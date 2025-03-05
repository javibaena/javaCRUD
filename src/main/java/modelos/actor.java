
package modelos;


public class actor {
   public int id;
   private String firstName;
   private String lastName;

    public actor(int actoId, String firstName, String lastName) {
        this.id = actoId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public actor() {
    }
        //getter
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
   
   
}
