package com.cristian.business;

public class VirusDAO {

    private String name;
    private String infectivityLevel;
    private String geneticSequence;
    private VirusFastaWriter virusFastaWriter;

    public VirusDAO(String name, String infectivityLevel, String geneticSequence, VirusFastaWriter virusFastaWriter){
        this.name = name;
        this.infectivityLevel = infectivityLevel;
        this.geneticSequence = geneticSequence;
        this.virusFastaWriter = virusFastaWriter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfectivityLevel() {
        return infectivityLevel;
    }

    public void setInfectivityLevel(String infectivityLevel) {
        this.infectivityLevel = infectivityLevel;
    }

    public String getGeneticSequence() {
        return geneticSequence;
    }

    public void setGeneticSequence(String geneticSequence) {
        this.geneticSequence = geneticSequence;
    }

    public VirusFastaWriter getEscritorFastaVirus() { return virusFastaWriter; }

    public void setEscritorFastaVirus(VirusFastaWriter virusFastaWriter) { this.virusFastaWriter = virusFastaWriter; }

    public String register(){
        StringBuilder sb = new StringBuilder();
        sb.append(">").append(this.name).append("|").append(this.infectivityLevel).append("\n").append(this.geneticSequence);
        return virusFastaWriter.writeFasta(sb.toString(), this.name + ".fasta");
    }
}
