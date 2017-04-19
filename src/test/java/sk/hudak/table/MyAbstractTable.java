package sk.hudak.table;

import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudak on 3.11.2015.
 */
public abstract class MyAbstractTable<T> extends JPanel {

    private JTable table;

    private int modelIndex = 0;
    private List<T> data = new ArrayList<>(); // obsah tabulky

    private String sortProperty;
    private OrderDirection orderDirection;

    protected abstract List<T> readData(int offset, int pageSize, String sortField, OrderDirection orderDirection);

    public MyAbstractTable() {
        this(false);
    }

    public MyAbstractTable(boolean allowMultiSelection) {
        super(new GridLayout(1, 0));

        table = new JTable(new MyTableModel(), new MyTableColumnModel());


        JTableHeader tableHeader = table.getTableHeader();


        //table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setTableSelectionMode(allowMultiSelection);

        addHeaderOnClickListener();

//        JTableHeader header = table.getTableHeader();
//        header.setDefaultRenderer(new TableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//
//                return null;
//            }
//        });
//            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//            table.setFillsViewportHeight(true);
        table.setVisible(false);

        add(new JScrollPane(table));
    }


    public void markAsVisible() {
        reloadData();
        table.setVisible(true);
    }

    private void reloadData() {
        //TODO offset a pagging
        data = readData(0, 0, sortProperty, orderDirection);

//        table.getModel().setValueAt();
    }


    public void addColumn(final String propertyName, final String headerName, String sortProperty, int width) {

//        MyTableCellRenderer cellRenderer = new MyTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                System.out.println("tmp "+value);
//                //TODO
//                return new Label(value.toString());
//            }
////            @Override
////            public Component getCellRendererComponent(JTable table, T value, boolean isSelected, boolean hasFocus, int row, int column) {
////                //TODO
////                return new Label(value.toString());
////            }
//        };

        MyTableColumn column = new MyTableColumn(width);
        column.setPropertyName(propertyName);
        column.setHeaderValue(headerName);
        column.setSortProperty(sortProperty);
//        column.setCellRenderer(cellRenderer);

        //TODO nefunguje...
        TableCellRenderer headerRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                System.out.println("TOto to je value " + value);
                return new Label((String) value);
            }
        };
        column.setHeaderRenderer(headerRenderer);
        table.addColumn(column);
    }


    private void onHeaderClick(MyTableColumn column) {
        // ak nie je nastavena sort property, nerob nic
        if (!column.isSortPropertySet()) {
            return;
        }

        //sortuj iba ak je nastavena sort property
        column.reorder();

        this.orderDirection = column.getOrderDirection();
        this.sortProperty = column.getSortProperty();

        reloadData();
    }

    //--------------------------------------------

    private class MyTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return modelIndex - 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            T value = data.get(rowIndex);
            MyTableColumn column = (MyTableColumn) table.getColumnModel().getColumn(columnIndex);

            Object property = null;
            try {
                property = PropertyUtils.getProperty(value, column.getPropertyName());

            } catch (Exception e) {
                // TODO
                e.printStackTrace();
            }
            return property;
        }
    }


    private class MyTableColumnModel extends DefaultTableColumnModel {
        public MyTableColumnModel() {
        }
    }

    private class MyTableColumn extends TableColumn {

        private String sortProperty;
        private OrderDirection orderDirection;
        private String propertyName;


        public MyTableColumn(int width) {
            this(width, null, null);
        }

        public MyTableColumn(int width, TableCellRenderer cellRenderer) {
            this(width, cellRenderer, null);
        }

        public MyTableColumn(int width, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
            super(MyAbstractTable.this.modelIndex++, width, cellRenderer, cellEditor);
        }

        public void setSortProperty(String sortField) {
            this.sortProperty = sortField;
        }

        public String getSortProperty() {
            return sortProperty;
        }

        public boolean isSortPropertySet() {
            return sortProperty != null;
        }

        public void setOrderDirection(OrderDirection orderDirection) {
            this.orderDirection = orderDirection;
        }

        public void reorder() {
            if (orderDirection == null) {
                orderDirection = OrderDirection.ASC;
            } else {
                orderDirection = (orderDirection.equals(OrderDirection.ASC)) ? OrderDirection.DESC : OrderDirection.ASC;
            }

        }

        public OrderDirection getOrderDirection() {
            return orderDirection;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    private abstract class MyTableCellRenderer implements TableCellRenderer {

//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            return getCellRendererComponent(table, (T) value, isSelected, hasFocus, row, column);
//        }
//
//        public abstract Component getCellRendererComponent(JTable table, T value, boolean isSelected, boolean hasFocus, int row, int column);
    }

    private void addHeaderOnClickListener() {
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int columnIndex = table.columnAtPoint(event.getPoint());
                MyTableColumn column = (MyTableColumn) table.getColumnModel().getColumn(columnIndex);
                onHeaderClick(column);
            }
        });
    }

    private void setTableSelectionMode(boolean allowMultiselection) {
        table.setSelectionMode(allowMultiselection ? ListSelectionModel.MULTIPLE_INTERVAL_SELECTION : ListSelectionModel.SINGLE_SELECTION);
    }
}
