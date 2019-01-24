package com.issart.boryshev.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.issart.boryshev.model.ContactData;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else System.out.println("Wrong format: " + format);
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getTitle(),
                    contact.getFirstName(),
                    contact.getMiddleName(),
                    contact.getLastName(),
                    contact.getNickname(),
                    contact.getHomePhone(),
                    contact.getMobilePhone(),
                    contact.getWorkPhone(),
                    contact.getAddress(),
                    contact.getEmail1(),
                    contact.getEmail2(),
                    contact.getEmail3()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                .withTitle("test" + i)
                .withFirstName("name" + i)
                .withMiddleName("middlename" + i)
                .withLastName("surname" + i)
                .withNickname("nick" + i)
                .withHomePhone("7895412" + i)
                .withMobilePhone("+78955424" + i)
                .withWorkPhone("4561" + i)
                .withAddress("address" + i)
                .withEmail1("email1" + i + "@gmail.com")
                .withEmail2("email2" + i + "@gmail.com")
                .withEmail3("email3" + i + "@gmail.com"));
        }
        return contacts;
    }
}
