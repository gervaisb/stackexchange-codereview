package q154869;

interface  Medicine {

    Medicine None = new Medicine() {
        @Override
        public void on(Quarantine q) {
            q.diabetics().becomes(q.death());
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "None";
        }
    };

    Medicine Aspirin = new Medicine() {
        @Override
        public void on(Quarantine q) {
            q.feverish().becomes(q.healthy());
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "Aspirin";
        }
    };

    Medicine Antibiotic = new Medicine() {
        @Override
        public void on(Quarantine q) {
            q.tuberculous().becomes(q.healthy());
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }

        @Override
        public String toString() {
            return "Antibiotic";
        }
    };

    Medicine Insulin = new Medicine() {
        @Override
        public void on(Quarantine q) {
            if (isHotMix(q.getTreatment())) {
                q.healthy().becomes(q.feverish());
            } else {
                // Prevent None effects, done is this.combine
            }
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.remove(Medicine.None)
                    .plus(this);
        }

        private boolean isHotMix(Treatment treatment) {
            return treatment.contains(this) &&
                    treatment.contains(Medicine.Antibiotic);
        }

        @Override
        public String toString() {
            return "Insulin";
        }
    };

    Medicine Paracetamol = new Medicine() {
        @Override
        public void on(Quarantine quarantine) {
            if (isToxicMix(quarantine.getTreatment())) {
                quarantine.feverish().becomes(quarantine.death());
                quarantine.healthy().becomes(quarantine.death());
                quarantine.diabetics().becomes(quarantine.death());
                quarantine.tuberculous().becomes(quarantine.death());
            } else{
                quarantine.feverish().becomes(quarantine.healthy());
            }
        }

        private boolean isToxicMix(Treatment treatment) {
            return treatment.contains(this) &&
                    treatment.contains(Aspirin);
        }

        @Override
        public Treatment combine(Treatment treatment) {
            return treatment.plus(this);
        }
    };


    void on(Quarantine quarantine);

    Treatment combine(Treatment treatment);

}
