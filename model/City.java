package model;

import java.util.Random;

/**
 * City class
 * Represents the city the vampire is active in
 * contains a list of all humans
 */
public class City {
    // a city has a number of inhabitants
    private Human[] humans = new Human[50];
    // a list of names to choose for humans
    private String[] names = new String[] { "Ahmed Jacob",
            "Burckhard Heinz",
            "Henner Lindner",
            "Eleonore Seifert",
            "Cristina Bender",
            "Robby Geißler",
            "Silvana Finke",
            "Christa-Maria Budig B.Sc.",
            "Gunnar Dietz MBA.",
            "Hans Jörg Röhricht",
            "Natalja Mülichen-Mentzel",
            "Anastasia Fiebig-Paffrath",
            "Knut Bloch",
            "Ingbert Klemt-Pohl",
            "Herr Stanislav",
            "Kolerabi lindner",
            "Klothilde Stolze B.A.",
            "Univ.Prof. Dorit Römer",
            "Dipl.-Ing. Luitgard Lindner B.A.",
            "Prof. Friedhilde Neureuther",
            "Suzanne Dörr",
            "Dr.Klaus-Peter Kobelt B.Sc.",
            "Mathilde Jüttner",
            "Dipl.-Ing. Jane Heuser",
            "Gönül Hande",
            "Ing.Friedlinde Jacob",
            "Dipl.-Ing.Jerzy Biggen B.Eng.",
            "Prof.Gustav Gumprich",
            "Marion Davids",
            "Burckhard Preiß",
            "Thorsten Klotz",
            "Diane Wulf",
            "Jelena Mitschke",
            "Gertraut Schönland",
            "Henner Dippel B.Eng.",
            "Dr. Denise Dörschner",
            "Univ. Prof.Vesna Junck",
            "Herr Tassilo Stahr B.Eng.",
            "Dr.Janko Tintzmann, Ing.",
            "Catharina Seifert B.A.",
            "Dr.Pascal Gude MBA.",
            "Evamaria Stumpf-Salz",
            "Apostolos Radisch-Gute",
            "Peter Stey",
            "Wolf Rohleder",
            "Larissa Gerlach",
            "Raphaela Fiebig",
            "Songül Schweitzer",
            "Herr Edgar Hertrampf",
            "Babette Schüler-Sauer",
            "Ali Mosemann",
            "Michail Täsche",
            "Ing. Adolfine Trupp B.Eng.",
            "Rotraud Söding-Binner",
            "Tassilo Beckmann",
            "Fedor Wulff",
            "Natascha Faust",
            "Jochem Heß",
            "Benita Lindner-Schmiedt",
            "Matthäus Bien B.Sc.",
            "Greta Preiß",
            "Hüseyin Barth",
            "Frau Rebekka Binner",
            "Gottlob Ziegert-Gorlitz",
            "Vittorio Finke-Junck",
            "Dittmar Döring MBA.",
            "Ante Kallert",
            "Katerina Röhrdanz-Höfig",
            "Hassan Weihmann",
            "Prof. Piotr Jacobi Jäckel",
            "Dagobert Dobes",
            "Rocco Lange",
            "Jose Putz B.Sc.",
            "Helmuth Preiß-Noack",
            "Gundel Loos-Pohl",
            "Gundi Riehl",
            "Madlen Klemm",
            "Gino Schäfer",
            "Jasmina Hauffer B.Eng.",
            "Elke Holzapfel-Faust",
            "Wolf-Rüdiger Röhricht",
            "Frederik Wiek",
            "Dipl.-Ing. Liesa Heintze",
            "Frau Cornelia Hendriks",
            "Ing. Xenia Fischer",
            "Dragica Schweitzer-Jockel",
            "Othmar Hering",
            "Mechthilde Biggen",
            "Günther Schäfer",
            "Bertram Höfig B.Sc.",
            "Siegried Hänel",
            "Helena Ring-Winkler",
            "Eckhardt Kuhl",
            "Dipl.-Ing. Oswald Peukert",
            "Günter Kabus",
            "Adelbert Dietz-Gröttner",
            "Dipl.-Ing. Hansjörg Wilms",
            "Ingelore Möchlichen",
            "Dipl.-Ing. Dörte Hein MBA",
            "Lidia Mühle" };

    /**
     * populate the city with random humans, choosing a name from the list and an
     * age between 20 and 92
     */
    public void populateCity() {
        Random number = new Random();
        for (int i = 0; i < this.humans.length; i++) {
            String name = getName();
            int age = number.nextInt(70);
            Human human = new Human(name, age + 20);
            this.humans[i] = human;
        }
    }

    /**
     * get a random name from the list of names
     * 
     * @return a name as String
     */
    private String getName() {
        Random number = new Random();
        int value = number.nextInt(this.names.length);

        return this.names[value];
    }

    /**
     * get a random human from the list, which is not deceased
     * 
     * @return
     */
    public Human getHuman() {
        Random number = new Random();
        while (true) {
            int value = number.nextInt(this.humans.length);
            if (this.humans[value].getDeceased() != true) {
                return this.humans[value];
            }
        }
    }

}
