import java.util.Scanner;
import java.util.Date;


public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] articles;
        int noOfArticles;
        int articleNumber;

        boolean programRunning;


        articleNumber = 1000;
        articles = new int[10][3];
        programRunning=true;


        do {

            switch (menu()) {
                case 1:
                    System.out.print("Ange antal artiklar att lägga in: ");
                    noOfArticles=input();
                    if(noOfArticles >= 1) {
                        articles = insertArticles(articles, articleNumber, noOfArticles);
                        articleNumber += noOfArticles;
                    }
                    else {
                        System.out.println("Felaktig inmatning");
                        System.out.println();
                    }
                    break;
                case 2:
                    removeArticle(articles);
                    break;
                case 3:
                    printArticles(articles);
                    break;
                case 4:
                    //System.out.println("4. Försäljning");
                    //System.out.println("");
                    break;
                case 5:
                    //System.out.println("5. Orderhistorik");
                    //System.out.println("");
                    break;
                case 6:
                    //System.out.println("6. Sortera orderhistoriktabell");
                    //System.out.println("");
                    break;
                case 7:
                    programRunning = false;
                    break;
                default:
                    System.out.println("Försök igen, Ange 1-7");
                    System.out.println();
            } // end of switch case

        } while (programRunning);
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
            System.out.println();

            try {

                inputInt = Math.abs(Integer.parseInt(input));
                isRunning = false;

            } catch (Exception e) {
                System.out.println("Felaktig inmatning");
            }
        } while (isRunning);

        return inputInt;
    }


    public static int[][] insertArticles(int[][] articles, int articleNumber, int noOfArticles) {

        int articlesPrint;

        articles = checkFull(articles, noOfArticles);
        articlesPrint = noOfArticles;

        for (int i = 0; i < articles.length && noOfArticles > 0; i++) {
            int firstEmptyIndex = 0;
            for(int j = i; j < articles.length; j++){
                if(articles[j][0] == 0) {
                    firstEmptyIndex = j;
                    break;
                }
            }
            articles[firstEmptyIndex][0] = articleNumber++;
            articles[firstEmptyIndex][1] = (int) (Math.random() * 10 + 1);
            articles[firstEmptyIndex][2] = (int) (Math.random() * 1000 + 1);
            noOfArticles--;
        }
        System.out.println(articlesPrint + " Artiklar Inlagda!");
        System.out.println();

        return articles;
    }

    public static int[][] checkFull(int[][] articles, int noOfArticles) {

        int[][] tempArticles;
        int freeSlots = 0;

        for(int i = 0; i < articles.length; i++) {
            if(articles[i][0] == 0) {
                freeSlots += 1;
            }
        }
        if (freeSlots >= noOfArticles) {
            return articles;
        }

        tempArticles = new int[articles.length+(noOfArticles-freeSlots)][3];

        for (int i = 0; i < articles.length; i++) {
            System.arraycopy(articles[i], 0, tempArticles[i], 0, articles[i].length);
        }
            articles = tempArticles;

        return articles;
    }

    public static void removeArticle(int[][] articles) {

        int articleNumber;

        System.out.print("Ange artikelnummer: ");

        articleNumber = input();

        for (int i = 0; i < articles.length; i++) {

            if (articles[i][0] == articleNumber) {
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;
            }
        }

        System.out.println("Artikelnummer " + articleNumber + " Borttaget!");
        System.out.println();
    }

    public static void printArticles(int[][] articles) {

        int tempArticleNumberColumn;
        int tempAmountColumn;
        int tempPriceColumn;

        System.out.println("Artnr\t\tAntal\t\tPris");

        //sorting by ascending articlenumber
        for (int i = 0; i < articles.length; i++) {
            for (int j = i + 1; j < articles.length; j++) {
                if (articles[i][0] > articles[j][0]) {
                    tempArticleNumberColumn = articles[i][0];
                    articles[i][0] = articles[j][0];
                    articles[j][0] = tempArticleNumberColumn;

                    tempAmountColumn = articles[i][1];
                    articles[i][1] = articles[j][1];
                    articles[j][1] = tempAmountColumn;

                    tempPriceColumn = articles[i][2];
                    articles[i][2] = articles[j][2];
                    articles[j][2] = tempPriceColumn;
                }
            }
        } //end of sorting

            //prints articlenumber, amount and price
        for (int i = 0; i < articles.length; i++) {
            if(articles[i][0]!=0) {
                System.out.println(articles[i][0] + "\t\t" + articles[i][1] + "\t\t\t" + articles[i][2]);
            }
        }
        System.out.println();
    }
    /*
    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles) {

    }/*
    /*
    public static void printSales(int[][]sales, Date[] salesDate) {

    }

    public static void sortedTable(int[][]sales,  Date[] salesDate) {

    }
    */

}
