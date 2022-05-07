import java.util.Scanner;
import java.util.Date;


public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        int noOfArticles;
        int articleNumber;
        int[][] articles;
        int[][] sales;

        Date salesDate[];
        salesDate = new Date[0];

        boolean programRunning;

        articleNumber = 1000;
        articles = new int[10][3];
        sales = new int[100][3];
        programRunning=true;


        do {

            switch (menu()) {
                //Lägg till artiklar
                case 1:
                    System.out.println("\t==LÄGG TILL ARTIKLAR==");
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
                //Ta bort artiklar
                case 2:
                    printArticles(articles);
                    System.out.println("\t==TA BORT ARTIKLAR==");
                    removeArticle(articles);
                    break;
                //Visa artiklar
                case 3:
                    System.out.println("\t==VISA ARTIKLAR==");
                    printArticles(articles);
                    break;
                //Försäljning
                case 4:
                    printArticles(articles);
                    System.out.println("\t==FÖRSÄLJNING==");
                    sellArticle(sales, salesDate, articles);
                    break;
                //Orderhistorik
                case 5:
                    System.out.println("\t==ORDERHISTORIK==");
                    printSales(sales, salesDate);
                    break;
                //Sorterad orderhistoriktabell
                case 6:
                    System.out.println("\t==SORTERAD ORDERHISTORIKTABELL==");
                    sortedTable(sales, salesDate);
                    break;
                //Avsluta programmet
                case 7:
                    programRunning = false;
                    break;
                default:
                    System.out.println("Försök igen, Ange 1-7");
                    System.out.println();
            } // end of switch case

        } while (programRunning);
    } // end of mainmethod

    //Method which displays the menu and returns the input
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

    //Method for user input
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
                System.out.println("Felaktig inmatning, försök igen");
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
        int articleNumberToRemove;
        int endRun;
        articleNumberToRemove=-1;
        endRun=0;

        do {
            System.out.print("Ange artikelnummer som ska tas bort: ");

            articleNumber = input();

            for (int i = 0; i < articles.length; i++) {
                if (articles[i][0] == articleNumber) {
                    articleNumberToRemove = i;
                }
            }
            if (articleNumberToRemove == -1) {
                System.out.println("Artikelnummer finns ej");
                System.out.println();
            }

            else {
                articles[articleNumberToRemove][0] = 0;
                articles[articleNumberToRemove][1] = 0;
                articles[articleNumberToRemove][2] = 0;
                endRun++;
                System.out.println("Artikelnummer " + articleNumber + " Borttaget!");
                System.out.println();
            }
        }while(endRun<1);
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
    /*fixa så att man vid felaktig inläsning få ett nytt försök att skriva in istället för att bli tillbaka kastad
    till menyn */
    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles) {

        int amountToSell;
        int articleNumber;
        int articleNumberToSell;


        articleNumberToSell=-1;

        System.out.print("Ange artikelnummer på varan som ska säljas: ");
        articleNumber=input();

        for(int i = 0; i < articles.length; i++) {
            if (articles[i][0] == articleNumber) {
                articleNumberToSell = i;
            }
        }

        if (articleNumberToSell==-1) {
            System.out.println("Artikelnummer finns ej");
            System.out.println();
        }

        else {
            System.out.print("Hur många artiklar ska säljas?: ");
            amountToSell = input();

            if (articles[articleNumberToSell][1] >= amountToSell) {
                articles[articleNumberToSell][1] = articles[articleNumberToSell][1] - amountToSell;

                for (int i = 0; i < sales.length; i++) {
                    if (sales[i][0]==0) {
                        sales[i][0] = articles[articleNumberToSell][0];
                        sales[i][1] = amountToSell;
                        sales[i][2] = articles[articleNumberToSell][2];
                        break;
                    }
                }

                System.out.println(amountToSell + " Artiklar sålda till priset av " + articles[articleNumberToSell][2] + ":- per styck");
                System.out.println();
            }

            else {
                System.out.println("Finns ej tillräckligt med artiklar");
                System.out.println();
            }

        }
    }

    public static void printSales(int[][]sales, Date[] salesDate) {
        System.out.println("Artnr\t\tAntal\t\tPris");
        for(int i = 0; i < sales.length; i++) {
            if (sales[i][0]!=0)
            System.out.println(sales[i][0] + "\t\t" + sales[i][1] + "\t\t\t" + sales[i][2]);

        }
        System.out.println();
    }

    public static void sortedTable(int[][]sales,  Date[] salesDate) {

        int tempArticleNumberColumn;
        int tempAmountColumn;
        int tempPriceColumn;
        int [][]sortedSales;
        sortedSales = new int[100][3];

        for (int i = 0; i < sales.length; i++) {
                    System.arraycopy(sales[i], 0, sortedSales[i], 0, sales[i].length);
                }

        System.out.println("Artnr\t\tAntal\t\tPris");

        //sorting by ascending articlenumber
        for (int i = 0; i < sortedSales.length; i++) {
            for (int j = i + 1; j < sortedSales.length; j++) {
                if (sortedSales[i][0] > sortedSales[j][0]) {
                    tempArticleNumberColumn = sortedSales[i][0];
                    sortedSales[i][0] = sortedSales[j][0];
                    sortedSales[j][0] = tempArticleNumberColumn;

                    tempAmountColumn = sortedSales[i][1];
                    sortedSales[i][1] = sortedSales[j][1];
                    sortedSales[j][1] = tempAmountColumn;

                    tempPriceColumn = sortedSales[i][2];
                    sortedSales[i][2] = sortedSales[j][2];
                    sortedSales[j][2] = tempPriceColumn;
                }
            }
        } //end of sorting

        for(int i = 0; i < sortedSales.length; i++) {
            if (sortedSales[i][0] != 0)
                System.out.println(sortedSales[i][0] + "\t\t" + sortedSales[i][1] + "\t\t\t" + sortedSales[i][2]);
        }
        System.out.println();
    }


} //end of class
