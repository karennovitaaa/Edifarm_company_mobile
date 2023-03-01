package com.aditiyagilang.edifarm_company.model;

public class model_dashboard {

        private final Integer medsos;
        private final String name;

        public model_dashboard(Integer medsos, String name) {
            this.medsos = medsos;
            this.name = name;
        }


    public Integer getMedsos() {
            return medsos;
        }

        public String getName() {
            return name;
        }
    }


