package Model;

import Helper.LogHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Report {

    private final int maxElements;

    private String reportName;

    private int reportId;

    private List<ViewElement> elementList;

    //Constructor
    public Report() {
        this.reportId = 0;
        this.reportName = "";
        this.maxElements = 4;
        this.elementList = new ArrayList<>();
    }

    public Report(int reportId, String reportName) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.maxElements = 4;
        this.elementList = new ArrayList<>();
    }

    //setter
    public void setReportName(String name) {
        reportName = name;
    }

    public void setReportId(int id) {
        reportId = id;
    }

    //getter
    public String getReportName() {
        return reportName;
    }

    public int getReportId() {
        return reportId;
    }

    public int getListElementSize() {
        return this.elementList.size();
    }

    public ViewElement getViewEelementbyIndex(int index) {
        return this.elementList.get(index);
    }

    //Methods
    public boolean addViewElement(ViewElement newElement) {
        if (elementList.size() < maxElements) {
            newElement.setDiagramId(createViewElementID());
            elementList.add(newElement);
            return true;
        } else {
            LogHandler.add("Maximale Anzahl (" + Integer.toString(maxElements) + ") an Anzeigeelementen erreicht.");
            return false;
        }
    }

    public boolean removeViewElement(ViewElement removeElement) {
        if (elementList.size() > 0) {
            elementList.remove(removeElement);
            return true;
        } else {
            LogHandler.add("Keine Anzeigeelemente vorhanden.");
            return false;
        }
    }

    @Override
    public String toString() {
        return reportId + "/"
                + reportName + "/"
                + elementList;
    }

    private int createViewElementID() {
        int id = 0;
        List<Integer> IDs = new ArrayList<>();

        boolean search = true;

        while (search) {
            for (int i = 0; i < getListElementSize(); i++) {
                IDs.add(getViewEelementbyIndex(i).getDiagramId());
            }

            Collections.sort(IDs);

            for (int i = 0; i < IDs.size(); i++) {
                if (id == IDs.get(i)) {
                    id++;
                } else {
                    break;
                }
            }
            search = false;
        }
        return id;
    }
}
