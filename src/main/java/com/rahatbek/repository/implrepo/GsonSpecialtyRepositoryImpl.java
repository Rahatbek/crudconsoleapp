package com.rahatbek.repository.implrepo;

import com.rahatbek.Util;
import com.rahatbek.model.Specialty;
import com.rahatbek.model.Status;
import com.rahatbek.repository.SpecialtyRepository;
import com.rahatbek.view.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final static String FILE_NAME = "specialties.json";

    public GsonSpecialtyRepositoryImpl() {

    }

    @Override
    public Specialty getById(Long id) throws Exception {
        List<Specialty> specialties = stringToData(Util.read(FILE_NAME));

        Specialty current = null;

        for (Specialty c : specialties) {
            if (c.getId().equals(id)) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }
        throw new Exception(Message.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Long id) throws Exception {
        List<Specialty> specialties = stringToData(Util.read(FILE_NAME));

        for (Specialty c : specialties) {
            if (c.getId().equals(id)) {
                c.setStatus(Status.DELETED);
                break;
            }
        }

        Util.writeList(FILE_NAME, dataToString(specialties));
    }

    @Override
    public void update(Specialty item) throws Exception {
        getById(item.getId());
        save(item);
    }

    @Override
    public void save(Specialty item) {
        Util.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Specialty> getAll() throws Exception {
        return stringToData(Util.read(FILE_NAME));
    }

    @Override
    public Long getLastId() throws Exception {
        List<Specialty> specialties = stringToData(Util.read(FILE_NAME));
        Collections.sort(specialties, Comparator.comparing(Specialty::getId));

        if (specialties.size() != 0) {
            return specialties.get(specialties.size() - 1).getId();
        }
        return 0L;
    }

    @Override
    public List<Specialty> stringToData(List<String> items) throws Exception {
        List<Specialty> specialties = new ArrayList<>();

        for (String s : items) {
            String[] parts = s.split(", ");
            Specialty specialty = new Specialty();
            specialty.setId(Long.parseLong(parts[0]));
            specialty.setName(parts[1]);
            specialty.setStatus(Status.valueOf(parts[2]));
            specialties.add(specialty);
        }
        return specialties;
    }

    @Override
    public List<String> dataToString(List<Specialty> items) {
        List<String> data = new ArrayList<>();
        for (Specialty c : items) {
            data.add(dataToString(c));
        }
        return data;
    }

    @Override
    public String dataToString(Specialty item) {
        return item.getId() + ", " + item.getName() + ", " + item.getStatus();
    }
}
