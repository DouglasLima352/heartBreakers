package model;

public class Paciente {
	private int prontuarionNumber;
	private String photo;
	private String name;
	private String cpf;
	private String rg;
	private String birth_date;
	private String address;
	private String cell;
	private String weight;
	private String gender;
	private String blood_type;
	private String cid;
	private String complaint;
	private String disease_history;
	private String allergies;
	private String conduct;
	private String physical_state;
	private String exams;
	private String diagnostic_hypothesis;
	private String results;
	
	public int getProntuarionNumber() {
        return prontuarionNumber;
    }

    public void setProntuarionNumber(int prontuarionNumber) {
        this.prontuarionNumber = prontuarionNumber;
    }
    
    //Foto
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
	
    //Nome
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //CPF
    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    
    //RG
    public String getRG() {
        return rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }
    
    //Birth_date
    public String getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }
    
    //Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    //Cell
    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
    
    //Weight
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    //Gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    //BloodType
    public String getBloodType() {
        return blood_type;
    }

    public void setBloodType(String blood_type) {
        this.blood_type = blood_type;
    }
    
    //Cid
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    //Complaint
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
    
    //Disease_history
    public String getDiseaseHistory() {
        return disease_history;
    }

    public void setDiseaseHistory(String disease_history) {
        this.disease_history = disease_history;
    }
    
    //Allergies
    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
    //Conduct
    public String getConduct() {
        return conduct;
    }

    public void setConduct(String conduct) {
        this.conduct = conduct;
    }
    
    //Physical_state
    public String getPhysicalState() {
        return physical_state;
    }

    public void setPhysicalState(String physical_state) {
        this.physical_state = physical_state;
    }
    
    //Exams
    public String getExams() {
        return exams;
    }

    public void setExams(String exams) {
        this.exams = exams;
    }
    
    //Diagnostic_hypothesis
    public String getDiagnosticHypothesis() {
        return diagnostic_hypothesis;
    }

    public void setDiagnosticHypothesis(String diagnostic_hypothesis) {
        this.diagnostic_hypothesis = diagnostic_hypothesis;
    }
    
    //Results
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    
    //sobrescrever
    @Override
    public String toString() {
        return getName(); //Tranformar em String
        //return getCPF();
        //return getRG();
        //return getBirthDate();
        //return getAddress();
        //return getCell();
        //return getWeight();
        //return getGender();
        //return getBloodType();
        //return getCid();
        //return getComplaint();
        //return getDiseaseHistory();
        //return getAllergies();
        //return getConduct();
        //return getExams();
        //return getDiagnosticHypothesis();
        //return getResults();
    }

}
