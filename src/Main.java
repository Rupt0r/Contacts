import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
class Application {
    public Contact[] contacts = new Contact[10];
    public Scanner sc = new Scanner(System.in);
    public int index = 0, command = 0;
    public String name = "", number = "";

    public Application (){
        for (int i = 0; i < 10; i++) {
            contacts[i] = new Contact("~", "~");
        }
    }
    public void run() {
        while (command != 4) {
            System.out.print("Введите команду или его номер:\n1. Новый контакт\n2. Редактировать контакт\n3. Удалить контакт\n4. Сохранить и выйти\n> ");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                case "Новый контакт":
                    AddContact();
                    break;

                case "2":
                case "Редактировать контакт":
                    ChangeContact();
                    break;

                case "3":
                case "Удалить контакт":
                    DeleteContact();
                    break;

                case "4":
                case "Сохранить и выйти":
                    command = 4;
                    return;

                default:
                    System.out.print("Вы ввели несуществуюшую команду");

            }

            sc.nextLine();
            OutSystem();
        }
    }

    private void DeleteContact() {
        command = 3;
        System.out.print("Введите индекс контакта: ");
        int index_delete = GetIndex() - 1;
        if (index_delete == -2){
            System.out.println("Введён несуществующий индекс");
            return;
        }
        contacts[index_delete].SetContact("~", "~");
        for (int i = index_delete; i < 9; i++){
            contacts[i] = contacts[i + 1];
        }
        contacts[9].SetContact("~", "~");
        index--;
        System.out.println("Контакт успешно удалён");
    }

    private void AddContact () {
        command = 1;
        if (index == 10) {
            System.out.print("Вы достигли предела кол-ва контактов");
            return;
        }
        System.out.print("Введите имя и номер контакта через пробел: ");
        name = sc.next();
        number = sc.next();
        contacts[index].SetContact(name, number);
        index++;
    }

    private void ChangeContact () {
        command = 2;
        System.out.print("Введите команду:\n1. Редактировать\n2. Поменять местами\n> ");
        String comm = sc.nextLine();
        switch (comm) {
            case "1":
            case "Редактировать":
                System.out.print("Введите индекс контакта: ");
                int index_change = GetIndex() - 1;
                if (index_change == -2){
                    System.out.println("Введён несуществующий индекс");
                    return;
                }
                System.out.print("Хотите изменить имя, номер и всё\n('1. Имя', '2. Номер', '3. Всё')\n> ");
                String in = sc.next();
                name = contacts[index_change].GetContact()[0];
                number = contacts[index_change].GetContact()[1];
                switch (in) {
                    case "1":
                    case "Имя":
                        System.out.print("Введите имя: ");
                        name = sc.next();
                        break;
                    case "2":
                    case "Номер":
                        System.out.print("Введите номер: ");
                        number = sc.next();
                        break;
                    case "3":
                    case "Всё":
                        System.out.print("Введите имя и номер через пробел: ");
                        name = sc.next();
                        number = sc.next();
                        break;
                    default:
                        System.out.print("\nВведена несуществующая функция");
                        break;
                }
                contacts[index_change].SetContact(name, number);
                break;
            case "2":
            case "Поменять местами":
                System.out.print("Введите индексы контактов, которые хотите поменять, через пробел:  ");
                int index_f = GetIndex() - 1;
                int index_s = GetIndex() - 1;
                if ((index_f == -2) || (index_s == -2)){
                    System.out.println("Введён несуществующий индекс");
                    return;
                }
                Contact swap = contacts[index_s];
                contacts[index_s] = contacts[index_f];
                contacts[index_f] = swap;
                break;
            default:
                System.out.print("\nВведена несуществующая функция");
                break;
        }
    }
    private void OutSystem() {
        System.out.println("-----------------------------------");
        for (int i = 0; i < 10; i++) {
            if (i >= index){
                System.out.printf("%d. ...\n", i + 1);
            }
            else {
                System.out.printf("%d. %s %s\n", i + 1, contacts[i].GetContact()[0], contacts[i].GetContact()[1]);
            }
        }
        System.out.println("-----------------------------------");
    }

    private int GetIndex() {
        int check_index = sc.nextInt();
        if ((0 < check_index) && (check_index < index + 1)) {
            return check_index;
        }
        return -1;
    }
}
class Contact {
    private String name = "~";
    private String number = "~";
    public Contact (String name, String number) {
        this.name = name;
        this.number = number;

    }
    private boolean CheckData (String value) {
        return Objects.equals(value, "");
    }
    public void SetContact (String new_name, String new_number) {
        if (CheckData(new_name) || CheckData(new_number)) {
            System.out.println("Имя или номер телефона не введены");
            return;
        }
        this.name = new_name;
        this.number = new_number;
    }
    public String[] GetContact () {
        return new String[]{this.name, this.number};
    }
}