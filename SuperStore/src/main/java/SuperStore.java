
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.io.FileWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;

public class SuperStore {
    public static void main(String[] args) throws IOException, ParseException {

        List<Customer> customersList = new ArrayList<>();
        List<Order> ordersList = new ArrayList<>();
        List<Product> productsList = new ArrayList<>();
        List<Sales> salesList = new ArrayList<>();
        FileReader reader = new FileReader("SuperStore\\src\\main\\java\\SuperStoreOrders.csv");
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withHeader());
        Map<String, Customer> customersMap = new HashMap<String, Customer>();

        for (CSVRecord record : parser) {
            try {
                String orderId = record.get("Order ID");
                String customerId = record.get("Customer ID");
                String customerName = record.get("Customer Name");
                String segment = record.get("Segment");
                String country = record.get("Country");
                String city = record.get("City");
                String state = record.get("State");
                int postalCode = Integer.parseInt(record.get("Postal Code"));
                String region = record.get("Region");
                double sales = Double.parseDouble(record.get("Sales").replaceAll(",", "."));

                Customer customer = new Customer(customerId, customerName, orderId, segment, country, city, state,
                        postalCode, region, sales);

                String productId = record.get("Product ID");
                String category = record.get("Category");
                String subCategory = record.get("Sub-Category");
                String productName = record.get("Product Name");
                int quantity = Integer.parseInt(record.get("Quantity"));
                double discount = Double.parseDouble(record.get("Discount").replaceAll(",", "."));
                double profit = Double.parseDouble(record.get("Profit").replaceAll(",", "."));

                String orderDateStr = record.get("Order Date");
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date orderDate = dateFormat.parse(orderDateStr);

                String shipDateStr = record.get("Ship Date");
                Date shipDate = dateFormat.parse(shipDateStr);

                String shipMode = record.get("Ship Mode");

                Order order = new Order(orderId, customer, orderDate, shipDate, shipMode, sales);
                Product product = new Product(orderId, productId, productName, category, subCategory,quantity,sales);
                Sales sale = new Sales(orderId, order, product, quantity, discount, profit, sales, region);

                // Adds the objects to their respective lists
                customersList.add(customer);
                ordersList.add(order);
                productsList.add(product);
                salesList.add(sale);

                if (!customersMap.containsKey(customerId)) {
                    customersMap.put(customerId, customer);
                }
            } catch (NumberFormatException e) {
                // Logs the error
                System.err.println("Error in row: " + record.getRecordNumber() + " - " + e.getMessage());
                // Skips the current row and continue processing the next row
                continue;
            }
        }

        // Closes the reader and parser
        parser.close();
        reader.close();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        System.out.println("\nErrors display mistakes in the csv file that should be fixed/adjusted. \n");

        System.out.println("When navigating the menus,if the options are enumerated, use single digit numbers to select them, otherwise reply with what is requested from the prompt!\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");

        System.out.println("\n-------------------------------\nWelcome to Super Store systems!\n-------------------------------\n\n");

        // Main menu options
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Search for a customer by name");
            System.out.println("2. Search for a specific customer's orders (Type their full name, if necessary use option 1 first.)");
            System.out.println("3. Look through dataset statistics");
            System.out.println("4. Generate a sales report");
            System.out.println("5. Exit\n");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
                scanner.nextLine();
                continue; // Starts the loop again
            }
            // The main options
            if (option == 1) {
                scanner.nextLine();
                System.out.println("\nEnter customer name:");
                String name = scanner.nextLine();
                List<String> customerNames = searchCustomerByName(customersMap, name);
                if (customerNames.isEmpty()) {
                    System.out.println("\nNo customers found with that name\n");
                } else {
                    System.out.println("\nCustomers found:");
                    for (String customerName : customerNames) {
                        System.out.println(customerName);
                    }
                }
            } else if (option == 2) {
                Boolean firstFlag = true;
                Boolean secondFlag = true;

                while (firstFlag) {
                    scanner.nextLine();
                    double total = 0;
                    System.out.println("\nEnter the desired customers full name:");
                    String name = scanner.nextLine();
                    List<Order> customersOrdersList = getOrdersForCustomer(ordersList, name);

                    if (!customersOrdersList.isEmpty()) {
                        System.out.println("\nOrder Id:\tOrder Date:\t\t\tShip Date:\t\t\tShip Mode:\tSales:\t");

                        for (Order order : customersOrdersList) {
                            System.out.println(
                                    order.getOrderId() + "\t" + order.getOrderDate() + "\t" + order.getShipDate() + "\t"
                                            + order.getShipMode() + "\t" + order.getSales() + "\t");
                            total += order.getSales();
                        }
                        System.out.println("\nThe total amount of sales for the customer: " + total + "\n");
                        while (secondFlag) {
                            System.out.println("Please select one of the following:\n1.Input an order ID to see the details of a specific order.\n2.Close and return to Main Menu\n");
                            String orderOrMenu = scanner.nextLine();
                            if (orderOrMenu.equals("1")) {
                                System.out.println("\nEnter order ID(case sensitive):");
                                String orderId = scanner.nextLine();

                                List<Product> matchingProducts = new ArrayList<>();
                                for (Product product : productsList) {
                                    if (product.getOrderId().equals(orderId)) {
                                        matchingProducts.add(product);
                                    }
                                }
                                if (matchingProducts.isEmpty()) {
                                    System.out.println("No items match the search criteria.\n");
                                } else {
                                    System.out.println("\nThe number of orders with that ID: " + matchingProducts.size()+"\n");
                                    Double totalProductSales=0.0;
                                    for (Product product : matchingProducts) {
                                        System.out.println("Order Id: " + product.getOrderId());
                                        System.out.println("Product Id: " + product.getProductId());
                                        System.out.println("Product Name: " + product.getProductName());
                                        System.out.println("Category: " + product.getCategory());
                                        System.out.println("Sub-Category: " + product.getSubCategory());
                                        System.out.println("Quantity: " + product.getQuantity());
                                        System.out.println("Sales: " + product.getSales());
                                        totalProductSales+=product.getSales();

                                        System.out.println();
                                    }
                                    System.out.println("The total sales for the products with this order ID: "+totalProductSales+"\n");
                                }

                            } else if (orderOrMenu.equals("2")) {
                                System.out.println("\nReturning to Main Menu..\n");
                                secondFlag = false;
                                firstFlag = false;
                            } else
                                System.out.println("\nInvalid selection, select 1 or 2 to proceed.\n");

                        }

                    } else {
                        System.out.println("\nNo customers with that full name found.\nReturning to Main Menu...\n");
                        firstFlag = false;
                    }

                }


            } else if (option == 3) {
                boolean a = true;
                while (a) {

                    System.out.println(
                            "\nPlease write the number of the statistic you are looking for:\nDataset statistics:");
                    System.out.println(
                            "1. The average sales amount of the orders.\n2. The best customer (highest total sales)?");
                    System.out.println(
                            "3. The amount of customers per state.\n4. How many Corporate, Consumer and Home Office customers there are.\n5. The total sales per year.");
                    System.out.println(
                            "6. The total sales per region, West, East, Central and South.\n7. Back to Main Menu\n");

                    try {
                        option = scanner.nextInt();
                        if (option == 1) {
                            double sumSales = 0;
                            int countSales = 0;

                            for (Sales sale : salesList) {
                                if (sale.getSales() != 0) {
                                    sumSales += sale.getSales();

                                    countSales++;
                                }
                            }
                            if (countSales > 0) {
                                double averageSales = sumSales / countSales;

                                DecimalFormat df = new DecimalFormat("#.00");
                                String formattedAverageSales = df.format(averageSales);

                                System.out.println(
                                        "\nThe average sales amount of the orders is " + formattedAverageSales + "\n");
                            } else
                                System.out.println("\nThere are no recorded sales.\n");

                        } else if (option == 2) {

                            Map<String, Double> customerSales = new HashMap<>();

                            for (Sales sale : salesList) {
                                String customerId = sale.getOrder().getCustomer().getCustomerId();
                                double saleAmount = sale.getSales();
                                customerSales.put(customerId, customerSales.getOrDefault(customerId, 0.0) + saleAmount);
                            }

                            // Finds the customer with the highest total sales
                            String topCustomer = "";
                            double maxSales = 0.0;
                            for (String customerId : customerSales.keySet()) {
                                double totalSales = customerSales.get(customerId);
                                if (totalSales > maxSales) {
                                    topCustomer = customerId;
                                    maxSales = totalSales;
                                }
                            }
                            String topCustomerName = "";
                            for (Customer customer : customersList) {
                                if (customer.getCustomerId().equals(topCustomer)) {
                                    topCustomerName = customer.getCustomerName();
                                    break;
                                }
                            }

                            System.out.println(
                                    "\nCustomer " + topCustomerName + " has made the most total sales: " + maxSales);

                        } else if (option == 3) {
                            System.out.println("\nThe amount of customers per state is:\n");
                            Map<String, Set<String>> statesToCustomers = new HashMap<>();

                            for (Customer customer : customersList) {
                                String state = customer.getState();
                                String customerId = customer.getCustomerId();

                                // Checks if the segment already exists in the map
                                if (!statesToCustomers.containsKey(state)) {
                                    statesToCustomers.put(state, new HashSet<>());
                                }
                                statesToCustomers.get(state).add(customerId);
                            }

                            // Prints out the number of unique customers for each segment
                            for (Map.Entry<String, Set<String>> entry : statesToCustomers.entrySet()) {
                                String segment = entry.getKey();
                                int numCustomers = entry.getValue().size();
                                System.out.println(segment + ": " + numCustomers);
                            }

                        } else if (option == 4) {
                            Map<String, Set<String>> segmentsToCustomers = new HashMap<>();

                            for (Customer customer : customersList) {
                                String segment = customer.getSegment();
                                String customerId = customer.getCustomerId();

                                // Checks if the segment already exists in the map
                                if (!segmentsToCustomers.containsKey(segment)) {
                                    segmentsToCustomers.put(segment, new HashSet<>());
                                }
                                segmentsToCustomers.get(segment).add(customerId);
                            }
                            System.out.println("\nThe customers by segment are:");


                            // Print out the number of unique customers for each segment
                            for (Map.Entry<String, Set<String>> entry : segmentsToCustomers.entrySet()) {
                                String segment = entry.getKey();
                                int numCustomers = entry.getValue().size();
                                System.out.println(segment + ": " + numCustomers);
                            }

                        } else if (option == 5) {
                            double sumSales = 0;
                            int countSales = 0;

                            for (Sales sale : salesList) {
                                if (sale.getSales() != 0) {
                                    sumSales += sale.getSales();

                                    countSales++;
                                }
                            }
                            DecimalFormat df = new DecimalFormat("#.00");
                            String formattedTotalSales = df.format(sumSales);
                            System.out.println("\nNumber of Sales: " + countSales);
                            System.out.println("Total Sales Value: " + formattedTotalSales + "\n");

                        } else if (option == 6) {

                            HashMap<String, Double> regionSales = new HashMap<>();
                            for (Sales sale : salesList) {
                                String region = sale.getRegion();
                                double sales = sale.getSales();
                                if (regionSales.containsKey(region)) {
                                    regionSales.put(region, regionSales.get(region) + sales);
                                } else {
                                    regionSales.put(region, sales);
                                }
                            }
                            System.out.println("\nThe sales statistics per region are as follows:");

                            System.out.println("");
                            for (String region : regionSales.keySet()) {
                                double sales = regionSales.get(region);
                                System.out.println(region + ": " + String.format("%.2f", sales));

                            }
                            System.out.println("");

                        } else if (option == 7) {
                            a = false;
                        }
                        else if (option<=0 || option>7)
                        {
                            System.out.println("That number is either too high or too low for the menu choices, please try again.");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer value.\n");
                        scanner.nextLine();
                        continue;
                    }
                }
            } else if (option == 4) {
                generateSalesReport(salesList);


            } else if (option == 5) {
                System.exit(0);
            } else {
                System.out.println("Invalid option\n");
            }
        }

    }


    // The functiones called upon, the names are mostly self explanatory
    public static List<String> searchCustomerByName(Map<String, Customer> customersMap, String name) {
        List<String> customerNames = new ArrayList<>();
        for (Customer customer : customersMap.values()) {
            if (customer.getCustomerName().toLowerCase().contains(name.toLowerCase())) {
                customerNames.add(customer.getCustomerName());
            }
        }
        return customerNames;

    }

    public static List<Order> getOrdersForCustomer(List<Order> ordersList, String customerName) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : ordersList) {
            if (order.getCustomer().getCustomerName().equalsIgnoreCase(customerName)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
    public static void generateSalesReport(List<Sales> salesList) {
        double sumSales = 0;
        int countSales = 0;

        for (Sales sale : salesList) {
            if (sale.getSales() != 0) {
                sumSales += sale.getSales();
                countSales++;
            }
        }

        HashMap<String, Double> regionSales = new HashMap<>();
        for (Sales sale : salesList) {
            String region = sale.getRegion();
            double sales = sale.getSales();
            if (regionSales.containsKey(region)) {
                regionSales.put(region, regionSales.get(region) + sales);
            } else {
                regionSales.put(region, sales);
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String fileName = "sales-report_" + formatter.format(date) + ".txt";

        try {
            // Write report to file
            FileWriter fw = new FileWriter(fileName);
            fw.write("-Super Store sales report-\n\n\n\n");
            fw.write("The sales, divided by regions are as follows:\n\n");

            for (String region : regionSales.keySet()) {
                double sales = regionSales.get(region);
                fw.write(region + ": " + String.format("%.2f", sales) + "\n");
            }
            fw.write("\n\nThe total amount of sales is as follows:\n\n");
            fw.write("Number of Sales: " + countSales + "\n");
            fw.write("Total Sales Value: " + String.format("%.2f", sumSales) + "\n");
            fw.close();

            System.out.println("\nSales report generated and saved to " + fileName+"\n");
        } catch (IOException e) {
            System.out.println("\nError generating sales report: " + e.getMessage()+"\n");
        }
    }


}

