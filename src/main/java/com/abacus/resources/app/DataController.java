package com.abacus.resources.app;

import com.abacus.resources.exceptions.InternalServerException;
import com.abacus.resources.exceptions.NotFoundException;
import com.abacus.resources.exceptions.NotSupportedException;
import com.abacus.resources.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.abacus.resources.data.*;

import java.util.*;

@Controller
public class DataController {
    private static HashMap<Long, Data> dataStore = new HashMap<Long, Data>();
    //static {
    //    dataStore.put((long)1000, new Person(1000, "Samir", 10, "en_US"));
    //}
    @GetMapping("/resources/data/{data_id}")
    @ResponseBody
    public List<IData> getResource(@PathVariable("data_id") long data_id) {
        List <IData> retVal = new ArrayList<>();
        if (data_id == -1) {
            Iterator it = dataStore.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                retVal.add((IData)pair.getValue());
            }
        }
        else {
            Data dataToReturn = dataStore.get(data_id);
            if (dataToReturn == null) {
                throw new NotFoundException("Data not found");
            } else {
                retVal.add(dataToReturn);
            }
        }
        return retVal;
    }

    @GetMapping("/resources/data/all")
    @ResponseBody
    public List<IData> getResource() {
        List <IData> retVal = new ArrayList<>();
        Iterator it = dataStore.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            retVal.add((IData)pair.getValue());
        }
        return retVal;
    }

	@PutMapping("/resources/data/{data_id}")
    @ResponseBody
    public IData putResource(@PathVariable("data_id") long data_id, @RequestBody Person data) {
    	// for now get Data = new Person
        String dataType = data.getType();
        if (dataType == null)
            throw new InternalServerException("dataType is Null");
        if (dataType.equalsIgnoreCase("person")) {
            Person person = (Person) dataStore.get(data_id);
            if (person == null) {
                person = new Person();
            }
            person.setId(data_id);
            person.setName(((Person) data).getName());
            person.setAge(((Person) data).getAge());
            person.setLocale(((Person) data).getLocale());
            dataStore.put(data_id, person);
            Result r = new Result(data.getId(), "SUCCESS");
            System.out.println(r.getId());
            return r;
        }
        else {
            //return new Result(-1,"Data not a person, Only Person Data types are currently supported.");
            throw new NotSupportedException("Data not a person, Only Person Data types are currently supported");
        }
    }

    @PostMapping("/resources/data/post")
    @ResponseBody
    public IData postResource(@RequestBody Person data) {
        // for now get Data = new Person
        String dataType = data.getType();
        if (dataType == null)
            throw new InternalServerException("dataType is Null");
        if (dataType.equalsIgnoreCase("person")) {
            if (data.getId() == 0) {
                UUID myuuid = UUID.randomUUID();
                long highbits = myuuid.getMostSignificantBits();
                long lowbits = myuuid.getLeastSignificantBits();
                if (highbits < -1) highbits  = highbits * -1;
                if (lowbits < -1) lowbits  = lowbits * -1;
                data.setId(highbits + lowbits);
            }
            Person newPerson = new Person(data.getId(), ((Person)data).getName(), ((Person)data).getAge(), ((Person)data).getLocale());
            dataStore.put(data.getId(), newPerson);
            Result r = new Result(data.getId(),"SUCCESS");
            System.out.println(r.getId());
            return r;
        }
        else {
            //return new Result(-1,"Data not a person, Only Person Data types are currently supported.");
            throw new NotSupportedException("Data not a person, Only Person Data types are currently supported");
        }
    }

	@DeleteMapping("/resources/data/{data_id}")
    @ResponseBody
    public IData deleteResource(@PathVariable("data_id") long data_id)  {
        Data dataToReturn = null;
        if (dataStore.containsKey(data_id)) {
            dataToReturn = dataStore.get(data_id);
            dataStore.remove(data_id);
        }
        else {
            throw new NotFoundException("Unable to delete, Data not found");
        }
        return dataToReturn;
    }

    //@ExceptionHandler
    //void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
    //    response.sendError(HttpStatus.BAD_REQUEST.value());
    //}

    //@ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    //void handleBadRequests(HttpServletResponse response) throws IOException {
    //    response.sendError(HttpStatus.BAD_REQUEST.value());
    //}
}