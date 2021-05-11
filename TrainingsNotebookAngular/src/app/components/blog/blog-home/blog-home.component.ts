import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-blog-home',
  templateUrl: './blog-home.component.html',
  styleUrls: ['./blog-home.component.css']
})
export class BlogHomeComponent implements OnInit {

  posts: Post[] = [];

  constructor(private router: Router, private postService: PostService) { 
    
  }

  ngOnInit(): void {
    this.getAllPosts();
  }

  goToCreatePost() {
    this.router.navigateByUrl('/create-post');
  }

  getAllPosts() {
    this.postService.getAllPosts()
    .pipe(map(data => data.map(post => this.limitCharacters(post))))
    .subscribe(data => {
      this.posts = data;
    }, error => {
      throwError(error);
    })
  }

  private limitCharacters(post: Post) {
    let updatedPost: Post = post;
    updatedPost.content = post.content.slice(0,100) + "..."; 
    return updatedPost;
  }

  removePost(id: number) {
    this.postService.deletePost(id).subscribe(data =>{
      console.log(data);
      this.getAllPosts();
    }, error => {
      throwError(error);
    })
    console.log("Trying to remove post with id: " + id);
  }

  editPost(postId: number) {
    this.router.navigate(['/edit-post',postId]);
  }
}
