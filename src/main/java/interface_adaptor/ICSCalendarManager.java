package src.main.java.interface_adaptor;

import src.main.java.entity.PlanItem;
import src.main.java.use_case.Calendar;
import src.main.java.use_case.WeeklyPlanner;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ICSCalendarManager {
    private ICSConverter converter;
    private String fileName;

    public ICSCalendarManager(ICSConverter converter, String fileName) {
        this.converter = converter;
        this.fileName = fileName;
    }

    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(converter.getPlanItems());
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<PlanItem> planItems = (ArrayList<PlanItem>) ois.readObject();
            for (PlanItem item : planItems) {
                converter.addPlanItem(item);
            }
        }
    }
}
