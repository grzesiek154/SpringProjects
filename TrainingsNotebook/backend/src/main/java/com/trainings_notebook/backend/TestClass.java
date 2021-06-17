package com.trainings_notebook.backend;

import java.util.*;
import java.util.Comparator;

import static java.util.stream.Collectors.groupingBy;

public class TestClass {
    public static void main(String[] args) {

        PostingRecord postingRecord1 = new PostingRecord(50, 143, "ticket");
        PostingRecord postingRecord2 = new PostingRecord(50, 163, "ticket");
        PostingRecord postingRecord3 = new PostingRecord(60, 270, "hotel");
        PostingRecord postingRecord4 = new PostingRecord(70, 3, "car");
        PostingRecord postingRecord5 = new PostingRecord(60, 27, "hotel");
        PostingRecord postingRecord6 = new PostingRecord(70, 33, "car");
        PostingRecord postingRecord7 = new PostingRecord(100, 543, "reimbursement");
        PostingRecord postingRecord8 = new PostingRecord(50, 183, "ticket");

        List<PostingRecord> postingRecords = new ArrayList<>();
        postingRecords.add(postingRecord1);
        postingRecords.add(postingRecord2);
        postingRecords.add(postingRecord3);
        postingRecords.add(postingRecord4);
        postingRecords.add(postingRecord5);
        postingRecords.add(postingRecord6);
        postingRecords.add(postingRecord7);
        postingRecords.add(postingRecord8);

        Map<String, List<PostingRecord>> byDesc = postingRecords.stream().collect(groupingBy(PostingRecord::getDescription));

        int index = 0;
        List<PostingRecord> postingRecords2 = new ArrayList<>();

        for(int i = 0; i<postingRecords.size(); i++) {
            if(postingRecords.get(i).getDescription() == "reimbursement") {
                index = postingRecords.indexOf(postingRecords.get(i));
            }
        }
        Collections.swap(postingRecords, index,0);

        postingRecords2 = groupElements(postingRecords, postingRecords.size());
        //Collections.sort(postingRecords, Comparator.comparing(PostingRecord::getDescription));
        Collections.sort(postingRecords, Comparator.comparingInt(PostingRecord::getId));
        postingRecords.sort((a,b) -> a.getId() < b.getId() ? 1 : -1);

        postingRecords2.forEach(postingRecord -> System.out.println(postingRecord.getId() + " " + postingRecord.getAmount() + " " + postingRecord.getDescription()));
    }

    static void swapList(List list) {
        Collections.swap(list, 1, 0);
    }

    public interface DataMatcher {
        boolean matches(String date);
    }

    public static class PostingRecord {

        private int id;
        private int amount;
        private String description;

        public PostingRecord(int id, int amount, String description) {
            this.id = id;
            this.amount = amount;
            this.description = description;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    static List<PostingRecord> groupElements(List<PostingRecord> list, int n) {
        List<PostingRecord> groupedList = new ArrayList<>();
        boolean visited[] = new boolean[n];
        for(int i = 0; i < n; i++) {
            visited[i] = false;
        }

        for(int i = 0; i < n; i++) {

            if(!visited[i]) {
                groupedList.add(list.get(i));
                for(int j = i + 1; j < n; j++) {
                    if(list.get(i).getDescription() == list.get(j).getDescription()) {
                        visited[j] = true;
                        groupedList.add(list.get(j));
                    }
                }
            }
        }

        return groupedList;
    }


}
