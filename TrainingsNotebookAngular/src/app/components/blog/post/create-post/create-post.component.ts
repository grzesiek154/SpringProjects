import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { throwError } from 'rxjs';
import { CreatePostPayload } from 'src/app/models/CreatePostPayload';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  post: Post;
  postId: number;
  isUpdated = false;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private postService: PostService, private fb: FormBuilder) {
    this.post = {
      postName: '',
      content: ''
    }
  }

  ngOnInit(): void {
    this.createPostForm = this.fb.group({
      postName: ['', Validators.required],
      content: ['', Validators.required]

    });

    this.activatedRoute.paramMap.subscribe(params => {
      const id = +params.get('id');
      if (id) {
        this.postId = id;
        this.isUpdated = true;
        this.getPost(id);
      }
    })
  }

  sentPostAction() {
    this.post.postName = this.createPostForm.get('postName').value;
    this.post.content = this.createPostForm.get('content').value;

    if (this.isUpdated == false) {
      this.postService.createPost(this.post).subscribe((data) => {
        this.navigateToBlogHome();
      }, error => {
        throwError(error);
      })
    } else {
      this.post.id = this.postId;
      this.postService.updatePost(this.post).subscribe((data) => {
        this.navigateToBlogHome();
        this.isUpdated = false;
      }, error => {
        throwError(error);
      })
    }
  }

  navigateToBlogHome() {
    this.router.navigateByUrl('/blog');
  }

  private getPost(id: number) {
    this.postService.getPost(id).subscribe(post => {
      this.editPost(post);
    }, error => {
      console.log(error);
    })
  }

  private editPost(editedPost: Post) {
    this.createPostForm.patchValue({
      postName: editedPost.postName,
      content: editedPost.content
    })
  }
}
