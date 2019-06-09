package com.example.demo.module.controller;

import com.example.demo.annotation.UndoLog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/module")
public class UndoController {

  @UndoLog
  @GetMapping("undo")
  public String undo() {
    return "this is undo page";
  }
}