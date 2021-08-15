import java.time.LocalDate;
import java.util.Scanner;

public class MaterialManagement {
    public static Scanner sc = new Scanner(System.in);
    private Material[] list = new Material[0];

//    public void createList() {
//        System.out.println("Please input numbers of Product");
//        int size = sc.nextInt();
//        list = new Material[size];
//    }

    public void mainMenu() {
        System.out.println("1. Add new Meat");
        System.out.println("2. Add new CrispyFlour");
        System.out.println("3. Remove");
        System.out.println("4. Edit product");
        System.out.println("5. Show Material Lists");
        System.out.println("0. Exit");
        int option = sc.nextInt();
        actions(option);
    }

    private void actions(int option) {
        switch (option) {
            case 1:
                Meat meat = addMeat();
                addToList(meat);
                break;
            case 2:
                CrispyFlour flour = addCrispyFlour();
                addToList(flour);
                break;
            case 3:
                System.out.println("Please input Material Id");
                String id = sc.nextLine();
                int index = findMaterial(id);
                removeFromList(index);
            case 4:
                editMenu();
                break;
            case 5:
                showListIngredients();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public Meat addMeat() {
        sc.nextLine();
        System.out.println("Please input id");
        String id = sc.nextLine();
        System.out.println("Pleáe input type of Meats");
        String name = sc.nextLine();
        System.out.println("PLease input date of manufacturing (yyyy-mm-dd)");
        String manuDate = sc.nextLine();
        System.out.println("please input total quantity (kg)");
        double weight = sc.nextDouble();
        System.out.println("Please input price for 1 kg (1000 vnd)");
        int cost = sc.nextInt();

        return new Meat(id, name, LocalDate.parse(manuDate), cost, weight);
    }

    public CrispyFlour addCrispyFlour() {
        sc.nextLine();
        System.out.println("Please input id");
        String id = sc.nextLine();
        System.out.println("Please input type of Flour");
        String name = sc.nextLine();
        System.out.println("PLease input date of manufacturing (yyyy-mm-dd)");
        String manuDate = sc.nextLine();
        System.out.println("please input total amount");
        double amount = sc.nextDouble();
        System.out.println("Please input price for 1 pack");
        int cost = sc.nextInt();

        return new CrispyFlour(id, name, LocalDate.parse(manuDate), cost, amount);
    }

    public void addToList(Material material) {
        Material[] temp = new Material[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        temp[temp.length - 1] = material;
        list = temp;
    }

    public int findMaterial(String id) {
        int index = -1;

        for (int i = 0; i < list.length; i++) {
            if (list[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void removeFromList(int index) {
        Material[] temp = new Material[list.length - 1];
        for (int i = 0; i < list.length; i++) {
            if (i < index) {
                temp[i] = list[i];
            } else {
                temp[i] = list[i + 1];
            }
        }
        list = temp;
    }

    public void editMenu() {
        sc.nextLine();
        System.out.println("Please input Material Id");
        String id = sc.nextLine();
        int index = findMaterial(id);
        if (index == -1) {
            System.out.println("Can not find material Id in system, please check again");
            return;
        }

        int option = 0;

        do {
            System.out.println("1. Edit id");
            System.out.println("2. Edit name");
            System.out.println("3. Edit cost");
            System.out.println("4. Edit quantity");
            System.out.println("0. Back to main Menu");

            option = sc.nextInt();
            editMaterial(option, index);

        } while (option != 0);
    }

    public void editMaterial(int option, int index) {
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.println("Please input new Id");
                String id = sc.nextLine();
                list[index].setId(id);
                break;
            case 2:
                System.out.println("please input new Name");
                String name = sc.nextLine();
                list[index].setName(name);
                break;
            case 3:
                System.out.println("Please input new cose");
                int cost = sc.nextInt();
                list[index].setCost(cost);
                break;
            case 4:
                System.out.println("PLease input new Quantity");
                double quantity = sc.nextDouble();

                if (list[index] instanceof Meat) {
                    ((Meat) list[index]).setQuantity(quantity);
                } else if (list[index] instanceof CrispyFlour) {
                    ((CrispyFlour) list[index]).setQuantity(quantity);
                }
                break;
            case 0:
                mainMenu();
                break;

        }

    }

    public void showListIngredients() {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

}
