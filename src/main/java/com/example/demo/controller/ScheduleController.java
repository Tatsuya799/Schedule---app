package com.example.demo.controller;

import com.example.demo.model.Schedule;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Controller; // 変更: 画面を表示する設定
import org.springframework.ui.Model; // 追加: HTMLにデータを渡すための道具
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // @RestControllerから変更（画面表示モード）
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    // コンストラクタ
    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // --- 1. スケジュール一覧画面を表示する ---
    @GetMapping("/schedules")
    public String getAllSchedules(Model model) {
        // データベースから全データを取得
        List<Schedule> schedules = scheduleRepository.findAll();
        // 「schedules」という名前でHTML側にデータを渡す
        model.addAttribute("schedules", schedules);
        // templates/index.html を表示する
        return "index"; 
    }

    // --- 2. 予定を追加したあと、画面を再表示する ---
    @PostMapping("/schedules")
    public String createSchedule(@ModelAttribute Schedule schedule) {
        scheduleRepository.save(schedule);
        // 保存が終わったら、一覧画面（/schedules）に自動で戻る
        return "redirect:/schedules";
    }

    // --- 3. 予定を削除したあと、画面を再表示する ---
    @PostMapping("/schedules/delete/{id}") // 削除も画面から行いやすいようPostに変更
    public String deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
        // 削除が終わったら、一覧画面（/schedules）に自動で戻る
        return "redirect:/schedules";
    }

    /* 編集（PUT）などはHTMLのフォーム形式に合わせて調整が必要になるため、
       まずは上記の「表示・追加・削除」が動くことを最優先にしています！
    */
}