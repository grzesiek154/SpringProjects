import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { CommentPayload } from 'src/app/models/CommentPayload';
import { Post } from 'src/app/models/Post';
import { CommentService } from 'src/app/services/comment.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent implements OnInit {

  postId: number;
  post$: Observable<Post>;
  commentForm: FormGroup;
  commentPayload: CommentPayload;
  comments: CommentPayload[];
  
  constructor(private postService: PostService, private commentService: CommentService, private router: Router, private activatedRoute: ActivatedRoute) { 

    this.post$ = this.activatedRoute.paramMap.pipe(
      switchMap((params: ParamMap) =>  
        this.postService.getPost(params.get('id')) 
      )
    )
    this.activatedRoute.paramMap.subscribe(params => {
      this.postId = +params.get('id');
      console.log("post id: " + this.postId);
    })
    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required)
    });
    this.commentPayload = {
      text: '',
      postId: this.postId,
      userName: '',
      createdDate: ''
    };
  }

  ngOnInit(): void {
    this.getCommentsForPost();
  
  }

  backToAllPosts() {
    this.router.navigateByUrl("/blog")
  }

  postComment() {
    this.commentPayload.text = this.commentForm.get('text').value;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.commentForm.get('text').setValue('');
      this.getCommentsForPost();
    }, error => {
      throwError(error);
    })
  }

  private getCommentsForPost() {
   this.commentService.getAllCommnetsForPost(this.postId).subscribe(data => {
      this.comments = data;
      this.comments.forEach(commnet => {
        console.log("comment: " + commnet.createdDate);
      })
      
    }, error => {
      throwError(error);
    })
  }


  
}
