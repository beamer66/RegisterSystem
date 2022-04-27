import java.util.Scanner;
import java.util.Date;


public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        boolean programRunning = true;



        int [][]articles;
        int noOfArticles=0;
        int articleNumber=1000;

        articles = new int[10][3];

        //Setting intial values of articles array to "0"
        /*for (int i = 0; i < 10; i++) {
            articles[i][0] = 0;

        }
        for (int i = 0; i < 10; i++) {
            articles[i][1] = 0;
        }
        for (int i = 0; i < 10; i++) {
            articles[i][2] = 0;
        }*/

        do {

            switch (menu()) {
                case 1:

                    articles = insertArticles(articles, articleNumber, noOfArticles);
                    break;
                case 2:
                    removeArticle(articles);
                    break;
                case 3:
                    printArticles(articles);
                    break;
                case 4:
                    System.out.println("4. Försäljning");
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("5. Orderhistorik");
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("6. Sortera orderhistoriktabell");
                    System.out.println("");
                    break;
                case 7:
                    programRunning=false;
                    break;
                default:
                    System.out.println("Försök igen, Ange 1-7");
                    System.out.println("");
            } // end of switch case

        } while(programRunning);
    } // end of mainmethod

    public static int menu() {

            System.out.println("1. Lägg in artiklar");
            System.out.println("2. Ta bort artikel");
            System.out.println("3. Visa artiklar");
            System.out.println("4. Försäljning");
            System.out.println("5. Order historik");
            System.out.println("6. Sortera orderhistoriktabell");
            System.out.println("7. Avsluta");
            System.out.print("Ditt val: ");

            return input();
    }

    public static int input() {

        int inputInt = 0;

        String input;

        boolean isRunning = true;

        do {
            input = userInput.nextLine();

            try {
                inputInt = Integer.parseInt(input);
                isRunning = false;
            }
            catch (Exception e) {
                System.out.println("Felaktig inmatning");
            }
        } while (isRunning);

        return inputInt;
    }

    public static int[][] insertArticles(int[][]articles, int articleNumber, int noOfArticles) {

        System.out.print("Ange antal artiklar att lägga in: ");

        noOfArticles=input();

        articles = checkFull(articles, noOfArticles);

        for (int i = 0; i < noOfArticles && i < articles.length; i++) {
            articles[i][0] = articleNumber++;
            articles[i][1] = (int) (Math.random() * 10 + 1);
            articles[i][2] = (int) (Math.random() * 1000 + 1);
        }


        for (int i = 0; i < noOfArticles; i++) {
            System.out.println(articles[i][0] + "\t\t" + articles[i][1] + "\t\t\t" + articles[i][2]);
        }

        return articles;
    }

    public static int[][] checkFull(int[][]articles, int noOfArticles) {

        int [][]tempArticles;

        tempArticles = new int[noOfArticles][3];

        if (noOfArticles > articles.length) {
            for (int i = 0; i < articles.length; i++) {
                System.arraycopy(articles[i], 0, tempArticles[i], 0, articles[i].length);
            }
            articles = tempArticles;
        }

        return articles;
    }

    public static void removeArticle (int[][]articles) {

        int articleNumber;

        System.out.print("Ange artikelnummer: ");

        articleNumber=input();

        for (int i = 0; i < articles.length; i++) {

            if (articles[i][0]==articleNumber){
                articles[i][0]=0;
                articles[i][1]=0;
                articles[i][2]=0;
            }
        }

        System.out.println("Artikelnummer " + articleNumber + " Borttaget!");
    }

    public static void printArticles (int[][]articles) {

        System.out.println("Artnr\t\tAntal\t\tPris");
        for (int i = 0; i < articles.length; i++) {

            //ifstatement to correct intendention if value is 0
            if (articles[i][0] == 0 && articles[i][1] == 0 && articles[i][2]==0) {
                System.out.println(articles[i][0] + "\t\t\t" + articles[i][1] + "\t\t\t" + articles[i][2]);
            }
            else {
                System.out.println(articles[i][0] + "\t\t" + articles[i][1] + "\t\t\t" + articles[i][2]);

            }
        }
    }

    /*
    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles) {

    }

    public static void printSales(int[][]sales, Date[] salesDate) {

    }

    public static void sortedTable(int[][]sales,  Date[] salesDate) {

    }
    */

}
