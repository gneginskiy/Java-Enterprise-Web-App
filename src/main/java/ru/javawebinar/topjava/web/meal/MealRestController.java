package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * greg neginsky
 * 06.03.2015.
 */
@RestController
@RequestMapping(value = MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    public static final String REST_URL = "/rest/meals";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);

        URI uriOfNewResourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(uriOfNewResourse).body(created);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam(value = "startTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                           @RequestParam(value = "endTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
