package com.abacus.resources.app;

import com.abacus.resources.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.abacus.resources.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DataController {
    private static HashMap<Long, Data> dataStore = new HashMap<Long, Data>();
    //static {
    //    dataStore.put((long)1000, new Person(1000, "Samir", 10, "en_US"));
    //}
    @GetMapping("/resources/data/{data_id}")
    @ResponseBody
    public List<IData> getResource(@PathVariable("data_id") long data_id) {
    	// for now get Data = new Person
        Data dataToReturn = dataStore.get(data_id);
        List <IData> retVal = new ArrayList<>();
        if (dataToReturn == null) {
            retVal.add(new Result("Data not found"));
        }
        else {
            retVal.add(dataToReturn);
        }
        return retVal;
    }

	@PutMapping("/resources/data/put")
    @ResponseBody
    public IData putResource(@RequestBody Person data) {
    	// for now get Data = new Person
        String dataType = data.getType();
        if (dataType.equalsIgnoreCase("person")) {
            Person newPerson = new Person(data.getId(), ((Person)data).getName(), ((Person)data).getAge(), ((Person)data).getLocale());
            dataStore.put(data.getId(), newPerson);
            return new Result("SUCCESS");
        }
        else {
            return new Result("Data not a person, Only Person Data types are currently supported.");
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
            return new Result("Unable to delete, Data not found");
        }
        return dataToReturn;
    }
}