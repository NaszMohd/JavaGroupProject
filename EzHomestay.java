import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class EzHomestay {
    public static void main(String[] args) {
        //Home Page
        String[] options = {"Sign Up", "Log In"};
        int userInput = JOptionPane.showOptionDialog(null, "Welcome to EzHomestay", "HOME", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (userInput == 0) {
            signUp();// sign up
        } else if (userInput == 1) {
                if (logIn()) {// log in

                } else {
                    JOptionPane.showMessageDialog(null, "The username or password you have entered is invalid!");
                    logIn();
                }
        } else if (userInput == JOptionPane.CLOSED_OPTION) {
            stopProgram(); //red X button
        }
    }

    public static void signUp() {
        // create a panel with two text fields for username and password
        String[] labels = {"Username:", "Password:"};
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(new JLabel(labels[0]));
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(15)); // a spacer
        panel.add(new JLabel(labels[1]));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Sign Up", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            usernameField.getText();
            passwordField.getPassword();
            // write the user's input to the file
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("userData.txt", true));
                bw.write(usernameField.getText() + "," + new String(passwordField.getPassword()));
                bw.newLine();
                bw.close();
                JOptionPane.showMessageDialog(null, "Sign Up Successful! Please Log In.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                logIn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        main(null);
    }

    
    public static boolean logIn() {
        // create a panel with two text fields for username and password
        String[] labels = {"Username:", "Password:"};
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(new JLabel(labels[0]));
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(15)); // a spacer
        panel.add(new JLabel(labels[1]));
        panel.add(passwordField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Log In", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // read user information from file and return true if credentials are correct, false otherwise
            try {
                BufferedReader br = new BufferedReader(new FileReader("userData.txt"));
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String line;
                while ((line = br.readLine()) != null) {
                    String[] userInfo = line.split(",");
                    if (userInfo[0].equals(username) && userInfo[1].equals(password)) {
                        br.close();
                        
                        JOptionPane.showMessageDialog(null, "Welcome "+username+"!!!", "EzHomestay Booking Application",1);
                        selectHomestay();
                    }
                }
                br.close();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    public static void stopProgram() {
        // stops the program when the red X button is clicked
        int userInput = JOptionPane.showConfirmDialog(null, "Click YES if you want to exit.", "Exit", JOptionPane.YES_NO_OPTION);
        if (userInput == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static String message(){
       return "                                            WELCOME TO EzHomestay BOOKING APPS !!!\n\n"+                                         
       "                                                      TANJONG MALIM HOMESTAY LIST :\n\n"+
       "          HOMESTAY BUDGET                                                                                 RM143 / NIGHT\n"+
       "          FAFA HOMESTAY TG. MALIM                                                                    RM100 / NIGHT\n"+
       "          THE RETREAT APARTMENT                                                                     RM407 / NIGHT";
    }

    public static void selectHomestay() {
        // Homestay selection option
        String[] homestay = {"HOMESTAY BUDGET", "FAFA HOMESTAY", "THE RETREAT APARTMENT"};
        String mess = message();
        int selectedHouse = JOptionPane.showOptionDialog(null, mess, "Homestay Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, homestay, homestay[0]);
        int pricePerNight = 0;
        if (selectedHouse == 0) {
            ImageIcon hb = new ImageIcon(EzHomestay.class.getResource("/homestayBudgett.jpg"));
                JOptionPane.showMessageDialog(
                        null,
                        "HOMESTAY BUDGET\nPRICE : RM143/NIGHT\n2 BEDROOM 2 BED",
                        "HOMESTAY BUDGET", JOptionPane.INFORMATION_MESSAGE,hb);
                        
                        pricePerNight = 143;
                                                                 
        } else if (selectedHouse == 1) {
            ImageIcon fafa = new ImageIcon(EzHomestay.class.getResource("/fafa.jpg"));
                JOptionPane.showMessageDialog(
                        null,
                        "FAFA HOMESTAY\nPRICE : RM100/NIGHT\n1 BEDROOM 2 BED",
                        "FAFA HOMESTAY TG. MALIM", JOptionPane.INFORMATION_MESSAGE,fafa);
            pricePerNight = 100;
        } else if (selectedHouse == 2) {
            ImageIcon rt = new ImageIcon(EzHomestay.class.getResource("/retreat.jpg"));
                JOptionPane.showMessageDialog(
                        null,
                        "THE RETREAT APARTMENT\nPRICE : RM407/NIGHT\n3 BEDROOM 4 BED",
                        "THE RETREAT APARTMENT", JOptionPane.INFORMATION_MESSAGE,rt);
            pricePerNight = 407;
        } else{
            stopProgram(); // X button
        }

        // ask for start date and end date
        String startDate = JOptionPane.showInputDialog("Enter the start date (dd/mm/yyyy):");
        String endDate = JOptionPane.showInputDialog("Enter the end date (dd/mm/yyyy):");
        int totalDays = calculateTotalDays(startDate, endDate);

        // check availability of date and house in file
        if (isDateAvailable(startDate, endDate, selectedHouse)) {
            // write booking in file
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("bookingData.txt", true));
                bw.write(startDate + "," + endDate + "," + selectedHouse);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            askToProceed(selectedHouse, pricePerNight, totalDays);
        } else {
            // ask user to choose different date
            JOptionPane.showMessageDialog(null, "The selected dates are not available. Please choose different dates.");
            selectHomestay();
        }
    }

    public static boolean isDateAvailable(String startDateStr, String endDateStr, Integer selectedHouse) {
        File file = new File("bookingData.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            if (startDate.after(endDate)) {
                JOptionPane.showMessageDialog(null, "The end date should be later than start date");
                return false;
            }
            BufferedReader br = new BufferedReader(new FileReader("bookingData.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookedDates = line.split(",");
                int bookedHouse = Integer.parseInt(bookedDates[2]);
                if (bookedHouse == selectedHouse) {
                    Date bookedStartDate = dateFormat.parse(bookedDates[0]);
                    Date bookedEndDate = dateFormat.parse(bookedDates[1]);
                    if ((startDate.after(bookedStartDate) && startDate.before(bookedEndDate)) 
                        || (endDate.after(bookedStartDate) && endDate.before(bookedEndDate)) 
                        || (startDate.equals(bookedStartDate) || endDate.equals(bookedEndDate))) {
                        br.close();
                        JOptionPane.showMessageDialog(null, "The selected dates are not available. Please choose different dates.");
                        return false;
                    }
                }
            }
            br.close();
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public static void askToProceed(int selectedHouse, int pricePerNight, int totalDays) {
        // ask user if they want to proceed with booking
        int userInput = JOptionPane.showConfirmDialog(null, "Do you want to proceed with booking?", "Booking", JOptionPane.YES_NO_OPTION);
        if (userInput == JOptionPane.YES_OPTION) {
            int totalPrice = totalDays * pricePerNight;
            getUserDetails(totalPrice, totalDays);
        } else if (userInput == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Booking canceled.");
            main(null);
        } else {
            // log out
            JOptionPane.showMessageDialog(null, "Logging out...");
            System.exit(0);
        }
    }

    public static void getUserDetails(int totalPrice, int totalDays) {
        // ask for user details
        String name = JOptionPane.showInputDialog("Enter your full name:");
        String email = JOptionPane.showInputDialog("Enter your email:");
        String phone = JOptionPane.showInputDialog("Enter your phone number:");

        // show details of booking with total price
        String bookingDetails = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nTotal Price: RM" + totalPrice + " for " + totalDays + " day(s)";
        JOptionPane.showMessageDialog(null, bookingDetails);

        // select payment method
        String[] paymentOptions = {"Paypal", "Debit/Credit card", "Cash"};
        int selectedOption = JOptionPane.showOptionDialog(null, "Select a payment method:", "Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, paymentOptions, paymentOptions[0]);
        if (selectedOption == 0) {
            // Paypal selected
        } else if (selectedOption == 1) {
            // Credit card selected
        } else if (selectedOption == 2) {
            // Cash selected
        }

        JOptionPane.showMessageDialog(null, "The payment details will be sent to " + email + ".");
        int userInput = JOptionPane.showConfirmDialog(null, "Do you want to proceed with the apps?", "Redirecting...", JOptionPane.YES_NO_OPTION);
        if (userInput == JOptionPane.YES_OPTION) {
            selectHomestay();
        } else if (userInput == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Logging out... ");
            System.exit(0);
        }
        System.exit(0);
    }

    public static int calculateTotalDays(String startDateStr, String endDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            long diff = endDate.getTime() - startDate.getTime();
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    public static int getDaysInMonth(int month, int year) {
        // returns the total number of days in a month (1-January = 31 days, 2-February = 29 or 28 days)
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }
}

class Homestay {
    // base class for Homestay
    private int pricePerNight;

    public Homestay(int pricePerNight) { //constructor
        this.pricePerNight = pricePerNight;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }
}

class HomestayBudget extends Homestay {
    // subclass for Homestay Budget 
    public HomestayBudget() {
        super(143);
    }
}

class FafaHomestay extends Homestay {
    // subclass for Fafa Homestay 
    public FafaHomestay() {
        super(100);
    }
}

class TheRetreatApartment extends Homestay {
    // subclass for The Retreat Apartment 
    public TheRetreatApartment() {
        super(407);
    }
}