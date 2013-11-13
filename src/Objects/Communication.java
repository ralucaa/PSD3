package Objects;

public class Communication {
        
        public static void showWelcomeMessage() {
                System.out.println("Welcome to TeamKMoodle!");
        };
        
        public static void showExitMessage() {
                System.out.println("Thank you for using TeamKMoodle!");
        };
                
        public static void printInvalidInput() {
                System.out.println("Invalid input.");
        }
        
        public static void printInformationNotAvailable() {
                System.out.println("The information you have requested is not available.");
        }
        
        public static void displaySeparator() {
                System.out.println("================================================================================================\n");
        }
}