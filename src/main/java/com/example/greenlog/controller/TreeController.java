package com.example.greenlog.controller;

import com.example.greenlog.dto.TreeRequest;
import com.example.greenlog.entity.Tree;
import com.example.greenlog.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    public ResponseEntity<Tree> createTree(@Valid @RequestBody TreeRequest request) {
        return new ResponseEntity<>(treeService.createTree(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tree>> getAllTrees() {
        return ResponseEntity.ok(treeService.getAllTrees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tree> getTreeById(@PathVariable Long id) {
        return ResponseEntity.ok(treeService.getTreeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tree> updateTree(@PathVariable Long id, @Valid @RequestBody TreeRequest request) {
        return ResponseEntity.ok(treeService.updateTree(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        treeService.deleteTree(id);
        return ResponseEntity.noContent().build();
    }
}