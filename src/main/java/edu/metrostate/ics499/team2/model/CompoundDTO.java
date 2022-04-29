package edu.metrostate.ics499.team2.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CompoundDTO {
    private ArrayList<Data> data;
    private String userId;

    @JsonCreator
    public CompoundDTO(ArrayList<Data> data, String userId) {
        this.data = data;
        this.userId = userId;
    }

    public ArrayList<Data> getData() {
        return this.data;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getConcatPayload() {
        return getMappedPayload().entrySet().stream().map(entry -> entry.getKey() + entry.getValue()).collect(Collectors.joining());
    }

    public HashMap<String, Integer> getMappedPayload() {
        HashMap<String, Integer> molecule = new HashMap<>();

        for (Data d : data) {
            molecule.put(d.getElement(), d.getNumberOfAtoms());
        }

        return molecule;
    }

    static class Data {
        private String element;
        private int numberOfAtoms;

        @JsonCreator
        public Data(String element, int numberOfAtoms) {
            this.element = element;
            this.numberOfAtoms = numberOfAtoms;
        }

        public String getElement() {
            return this.element;
        }

        public int getNumberOfAtoms() {
            return this.numberOfAtoms;
        }
    }
}
