/*A program which works as a simple cashregister. the user can add and remove articles show articles that have been added.
user can also sell articles and view sales history with time stamps of time and date of the sale.
the sales history can also be sorted by ascending articlenumber*/

import java.util.Scanner;
import java.util.Date;


public class Main {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        int noOfArticles;
        int articleNumber;
        int[][] articles;
        int[][] sales;

        Date[] salesDate;

        boolean programRunning;

        articleNumber = 1000;
        articles = new int[10][3];
        salesDate = new Date[0];
        sales = new int[0][3];
        programRunning=true;

        // Do while loop which lets the user choose any of the options 1-7 until option 7 which terminates the program is chosen
        do {

            switch (menu()) {
                //Add articles
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
                //Remove articles
                case 2:
                    printArticles(articles);
                    System.out.println("\t==TA BORT ARTIKLAR==");
                    removeArticle(articles);
                    break;
                //Show articles
                case 3:
                    System.out.println("\t==VISA ARTIKLAR==");
                    printArticles(articles);
                    break;
                //Sales
                case 4:
                    printArticles(articles);
                    System.out.println("\t==FÖRSÄLJNING==");
                    salesDate=insertDate(salesDate); //adds 1 index to the salesDate array
                    sales=insertSale(sales); //adds 1 index to the sales 2D-array
                    sellArticle(sales, salesDate, articles);
                    break;
                //Order history
                case 5:
                    System.out.println("\t==ORDERHISTORIK==");
                    printSales(sales, salesDate);
                    break;
                //Sorted order history
                case 6:
                    System.out.println("\t==SORTERAD ORDERHISTORIKTABELL==");
                    sortedTable(sales, salesDate);
                    break;
                //Terminate program
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

        int inputInt;

        inputInt = 0;

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

    //Method which adds desiered number of articles from user input and then calling the checkFull method and then returns the new value
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

    //Method which checks if the articles 2d-array is full and adds missing slots and returns the new value
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

    //Method which adds an index to the sales 2d-array and returns the new value
    public static int[][] insertSale(int[][]sales) {

        int [][]tempSales;

        tempSales = new int[sales.length+1][3];

        for (int i = 0; i < sales.length; i++) {
            System.arraycopy(sales[i], 0, tempSales[i], 0, sales[i].length);
        }
        sales = tempSales;

        return sales;

    }

    //Method which adds an index to the date array and returns the new value
    public static Date [] insertDate(Date[] salesDate) {
        Date []tempDate;

        tempDate = new Date[salesDate.length+1];

        for (int i = 0; i < salesDate.length; i++) {
            System.arraycopy(salesDate, 0, tempDate, 0, salesDate.length);
        }
        salesDate = tempDate;

        return salesDate;

    }

    //Method which removes an article including amount and price from users input
    public static void removeArticle(int[][] articles) {

        int articleNumber;
        int articleNumberToRemove;
        int endRun;

        articleNumberToRemove=-1;
        endRun=0;

        //Do while loop which allows user to enter article number again if entered number doesnt exist
        do {
            System.out.print("Ange artikelnummer som ska tas bort: ");

            articleNumber = input();

            if (articleNumber > 0) {
                for (int i = 0; i < articles.length; i++) {
                    if (articles[i][0] == articleNumber) {
                        articleNumberToRemove = i;
                    }
                }
                if (articleNumberToRemove == -1) {
                    System.out.println("Artikelnummer finns ej");
                    System.out.println();
                } else {
                    articles[articleNumberToRemove][0] = 0;
                    articles[articleNumberToRemove][1] = 0;
                    articles[articleNumberToRemove][2] = 0;
                    endRun++;
                    System.out.println("Artikelnummer " + articleNumber + " Borttaget!");
                    System.out.println();
                }
            }
            else
                System.out.println("Artikelnummer finns ej");
            System.out.println();
        }while(endRun<1); //end of do while loop
    }

    //Method which prints the articles and sorting by ascending article number
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

    //Method used for selling an article from user input also adding data to sale
    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles) {

        int amountToSell;
        int articleNumber;
        int articleNumberToSell;

        boolean getArticle = false;
        boolean getAmount = false;


        articleNumberToSell=-1;

        //to see if the articlenumber entered exists, lets user try again if it dont
        do {
            System.out.print("Ange artikelnummer på varan som ska säljas: ");
            articleNumber = input();
            if (articleNumber < 1000) {
                System.out.println("Artikelnummer finns ej");
            }
            else {
                for (int i = 0; i < articles.length; i++) {
                    if (articles[i][0] == articleNumber) {
                        articleNumberToSell = i;
                    }
                }
                if (articleNumberToSell == -1) {
                    System.out.println("Artikelnummer finns ej");
                    System.out.println();
                } else if (articles[articleNumberToSell][1] == 0) {
                    System.out.println("Det finns inga artiklar att sälja");
                } else
                    getArticle = true;
            }
        } while (!getArticle);

        //to see if there are enough articles, lets user try again if it dont
        do {
            System.out.print("Hur många artiklar ska säljas?: ");
            amountToSell = input();
            if (amountToSell>1) {
                if (articles[articleNumberToSell][1] < amountToSell) {
                    System.out.println("Finns ej tillräckligt med artiklar");
                    System.out.println();
                }
                else
                    getAmount = true;
            }
            else {
                System.out.println("Kan inte sälja 0st");
                System.out.println();
            }

        } while (!getAmount);

        //removing amount from the article
        articles[articleNumberToSell][1] = articles[articleNumberToSell][1] - amountToSell;

        //adding to sale
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][0]==0) {
                sales[i][0] = articles[articleNumberToSell][0];
                sales[i][1] = amountToSell;
                sales[i][2] = articles[articleNumberToSell][2];
                salesDate[i] = new Date();
                break;
            }
        }

        System.out.println(amountToSell + " Artiklar sålda till priset av " + articles[articleNumberToSell][2] + ":- per styck");
        System.out.println();

    }

    //a method which prints the sales to the user with date and time of the sale
    public static void printSales(int[][]sales, Date[] salesDate) {

        System.out.println("Artnr\t\tAntal\t\tPris");

        for(int i = 0; i < sales.length; i++) {
            if (sales[i][0]!=0)
                System.out.println(sales[i][0] + "\t\t" + sales[i][1] + "\t\t\t" + sales[i][2] + "\t\t" + salesDate[i]);

        }
        System.out.println();
    }

    //Method which sorts the sales by ascending article number prints the sales to the user with date and time
    public static void sortedTable(int[][]sales,  Date[] salesDate) {

        int tempArticleNumberColumn;
        int tempAmountColumn;
        int tempPriceColumn;
        int [][]sortedSales;

        Date[] sortedDate;
        Date[] tempdate;

        tempdate = new Date[salesDate.length];

        sortedDate = salesDate;
        sortedSales = new int[sales.length][3];

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

                    tempdate[i] = sortedDate[i];
                    sortedDate[i] = sortedDate[j];
                    sortedDate[j] = tempdate[i];

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
                System.out.println(sortedSales[i][0] + "\t\t" + sortedSales[i][1] + "\t\t\t" + sortedSales[i][2] + "\t\t" + sortedDate[i]);
        }
        System.out.println();
    }


} //end of class
