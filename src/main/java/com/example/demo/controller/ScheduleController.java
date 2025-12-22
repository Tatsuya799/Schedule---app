package com.example.demo.controller;

import com.example.demo.model.Schedule;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // --- 0. トップページ（RenderのURLでアクセス） ---
    @GetMapping("/")
    public String home(Model model) {
        List<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        return "index"; // templates/index.html を表示
    }

    // --- 1. スケジュール一覧画面 ---
    @GetMapping("/schedules")
    public String getAllSchedules(Model model) {
        List<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        return "index";
    }

    // --- 2. 予定を追加 ---
    @PostMapping("/schedules")
    public String createSchedule(@ModelAttribute Schedule schedule) {
        scheduleRepository.save(schedule);
        return "redirect:/schedules";
    }

    // --- 3. 予定を削除 ---
    @PostMapping("/schedules/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
        return "redirect:/schedules";
    }
}