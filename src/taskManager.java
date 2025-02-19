package src;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";
    private List<Task> tasks = new ArrayList<>();

    // Cargar tareas desde el archivo al iniciar
    public TaskManager() {
        loadTasksFromFile();
    }

    // Añadir tarea
    public void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile();
    }

    // Listar todas las tareas
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Devolver copia para evitar modificación directa
    }

    // Marcar tarea como completada
    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(true);
                saveTasksToFile();
                return;
            }
        }
        throw new IllegalArgumentException("Tarea no encontrada con ID: " + taskId);
    }

    // Eliminar tarea
    public void deleteTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
        saveTasksToFile();
    }

    // Guardar en archivo
    private void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.getId() + "," + task.getDescription() + "," + task.isCompleted());
            }
        } catch (IOException e) {
            System.err.println("Error guardando tareas: " + e.getMessage());
        }
    }

    // Cargar desde archivo
    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String desc = parts[1];
                boolean completed = Boolean.parseBoolean(parts[2]);
                Task task = new Task(id, desc);
                task.setCompleted(completed);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // Archivo no existe aún, se creará al añadir la primera tarea
        } catch (IOException e) {
            System.err.println("Error cargando tareas: " + e.getMessage());
        }
    }
}