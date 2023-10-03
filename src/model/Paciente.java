package model;

public class Paciente {
	private int prontuarionNumber;
	private String name;
	private String photo;
	
	public int getProntuarionNumber() {
        return prontuarionNumber;
    }

    public void setProntuarionNumber(int prontuarionNumber) {
        this.prontuarionNumber = prontuarionNumber;
    }
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //sobrescrever
    @Override
    public String toString() {
        return getName(); //Tranformar em String
    }

}
