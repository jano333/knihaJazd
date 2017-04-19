package sk.hudak.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudak on 3.11.2015.
 */
public class EshopTable extends MyAbstractTable<BlackBoxRecordListDto> {


    @Override
    protected List<BlackBoxRecordListDto> readData(int offset, int pageSize, String sortField, OrderDirection orderDirection) {
        System.out.println(">> EshopTable.readData");
        System.out.println("offset " + offset);
        System.out.println("pageSize " + pageSize);
        System.out.println("sortField " + sortField);
        System.out.println("orderDirection " + orderDirection);

        ArrayList<BlackBoxRecordListDto> blackBoxRecordListDtos = new ArrayList<>();
        BlackBoxRecordListDto dto = new BlackBoxRecordListDto();
        dto.setName("name1");
        dto.setVek(1);
        blackBoxRecordListDtos.add(dto);

        dto = new BlackBoxRecordListDto();
        dto.setName("name2");
        dto.setVek(2);
        blackBoxRecordListDtos.add(dto);

        dto = new BlackBoxRecordListDto();
        dto.setName("name3");
        dto.setVek(3);
        blackBoxRecordListDtos.add(dto);

        System.out.println("<< EshopTable.readData");

        return blackBoxRecordListDtos;
    }
}
