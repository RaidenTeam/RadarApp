package bg.mentormate.academy.radarapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.mentormate.academy.radarapp.R;
import bg.mentormate.academy.radarapp.data.LocalDb;
import bg.mentormate.academy.radarapp.models.Room;
import bg.mentormate.academy.radarapp.models.User;

public class EditRoomActivity extends ActionBarActivity implements View.OnClickListener {

    private LocalDb mLocalDb;
    private User mUser;
    private Button applyChangesBtn;
    private EditText editPassword;
    private EditText editRoomName;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        mLocalDb = LocalDb.getInstance();
        mUser = mLocalDb.getCurrentUser();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editPassword = (EditText) findViewById(R.id.changePass);
        confirmPassword = (EditText) findViewById(R.id.confirmChangePass);
        editRoomName = (EditText) findViewById(R.id.changeRoomName);
        applyChangesBtn = (Button) findViewById(R.id.applyChangesBtn);

        String currentRoomName = "";
        try {
            currentRoomName = mUser.getRoom().getName();
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.d("EDIT_ROOM:", "Unable to resolve current room name. Please check!");
        }
        editRoomName.setHint(currentRoomName);

        applyChangesBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.applyChangesBtn:
                boolean roomNameChanged = false;
                boolean passChanged = false;
                boolean passChangeAttempt = false;

                String newRoomName;
                newRoomName = editRoomName.getText().toString();
                String pass;
                pass = editPassword.getText().toString();
                String confirmPass;
                confirmPass = confirmPassword.getText().toString();

                if(!pass.isEmpty() && !confirmPass.isEmpty()) {
                    if (pass.equals(confirmPass)) {
                        mUser.setPassword(pass.trim());
                        passChanged = true;

                    } else {
                        passChangeAttempt = true;
                        Toast.makeText(getApplicationContext(), "The new password should be re-typed correctly, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                Room myRoom = mUser.getRoom();

                if(!newRoomName.isEmpty() && !newRoomName.equals(mUser.getRoom().getName().toString())){
                    myRoom.setName(newRoomName.trim());
                    mUser.setRoom(myRoom);

                    roomNameChanged = true;
                }

                if(roomNameChanged || passChanged){
                    mUser.saveInBackground();
                    if(passChangeAttempt) {
                        Toast.makeText(getApplicationContext(), getString(R.string.detailsHaveBeenSaved), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.allDetailsHaveBeenSaved), Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                }
                break;

        }

    }
}
