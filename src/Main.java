package src;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Gestor de Tareas ---");
            System.out.println("1. Añadir Tarea");
            System.out.println("2. Listar Tareas");
            System.out.println("3. Marcar Tarea Completada");
            System.out.println("4. Eliminar Tarea");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    System.out.print("Ingresa aqui la descripción de la tarea añadida: ");
                    String desc = scanner.nextLine();
                    int newId = manager.getAllTasks().size() + 1;
                    manager.addTask(new Task(newId, desc));
                    System.out.println("Tarea añadida con éxito!");
                    break;
                case 2:
                    List<Task> tasks = manager.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No hay tareas registradas.");
                    } else {
                        tasks.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Ingresa el ID de la tarea a marcar: ");
                    int completeId = scanner.nextInt();
                    manager.markTaskAsCompleted(completeId);
                    System.out.println("Tarea actualizada!");
                    break;
                case 4:
                    System.out.print("Ingresa el ID de la tarea a eliminar: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteTask(deleteId);
                    System.out.println("Tarea eliminada!");
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (choice != 5);
        scanner.close();
    }
}