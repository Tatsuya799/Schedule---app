package com.example.demo.controller;

import com.example.demo.model.Schedule;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 予定一覧を取得（GET）
    @GetMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // 予定を追加（POST）
    @PostMapping("/schedules")
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 予定を編集（PUT）
    @PutMapping("/schedules/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDate(scheduleDetails.getDate());
        schedule.setState(scheduleDetails.getState());
        schedule.setName(scheduleDetails.getName());
        schedule.setColor(scheduleDetails.getColor());

        return scheduleRepository.save(schedule);
    }

    // 予定を削除（DELETE）
    @DeleteMapping("/schedules/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
        return "Schedule deleted successfully!";
    }
}