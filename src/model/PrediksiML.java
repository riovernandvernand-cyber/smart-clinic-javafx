package model;

public class PrediksiML {

    private int idPrediksi;

    private int pregnancies;

    private int glucose;

    private int bloodPressure;

    private int skinThickness;

    private int insulin;

    private double bmi;

    private double dpf;

    private int age;

    private String hasilPrediksi;

    private double probabilitas;

    public PrediksiML() {
    }

    public int getIdPrediksi() {
        return idPrediksi;
    }

    public void setIdPrediksi(int idPrediksi) {
        this.idPrediksi = idPrediksi;
    }

    public int getPregnancies() {
        return pregnancies;
    }

    public void setPregnancies(int pregnancies) {
        this.pregnancies = pregnancies;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getSkinThickness() {
        return skinThickness;
    }

    public void setSkinThickness(int skinThickness) {
        this.skinThickness = skinThickness;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getDpf() {
        return dpf;
    }

    public void setDpf(double dpf) {
        this.dpf = dpf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHasilPrediksi() {
        return hasilPrediksi;
    }

    public void setHasilPrediksi(String hasilPrediksi) {
        this.hasilPrediksi = hasilPrediksi;
    }

    public double getProbabilitas() {
        return probabilitas;
    }

    public void setProbabilitas(double probabilitas) {
        this.probabilitas = probabilitas;
    }

}