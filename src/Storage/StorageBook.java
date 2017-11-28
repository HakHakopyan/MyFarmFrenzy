package Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StorageBook {
    private Stack<Record> book;

    public StorageBook() {
        this.book = new Stack<Record>();
        this.book.clear();
    }

    public  void setRecord(Record record) {
        this.book.push(record);
    }

    public  Record getRecord() {
        if (isEmpty())
            return null;

        return this.book.pop();
    }

    public boolean isEmpty() {
        return this.book.empty();
    }
}
