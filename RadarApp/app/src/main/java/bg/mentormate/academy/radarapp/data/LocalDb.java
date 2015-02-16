package bg.mentormate.academy.radarapp.data;

import bg.mentormate.academy.radarapp.models.Room;
import bg.mentormate.academy.radarapp.models.User;

/**
 * Created by tl on 14.02.15.
 */
public class LocalDb {

    private static LocalDb instance;

    public static LocalDb getInstance() {
        if (instance == null) {
            instance = new LocalDb();
        }

        return instance;
    }

    private User currentUser;
    private Room selectedRoom;

    private LocalDb() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }
}