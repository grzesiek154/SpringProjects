import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreatePostPayload } from '../models/CreatePostPayload';
import { Post } from '../models/Post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>('http://localhost:8080/api/posts/');
  }

  createPost(post: Post): Observable<any> {
    return this.http.post('http://localhost:8080/api/posts/', post);
  }

  updatePost(post: Post): Observable<any> {
    return this.http.post('http://localhost:8080/api/posts/update', post);
  }

  getPost(id: number | string): Observable<Post> {
    let idToNumber = Number(id);
    return this.http.get<Post>('http://localhost:8080/api/posts/by-id/' + idToNumber);
  }

  getAllPostsByUser(name: string): Observable<Post[]> {
    return this.http.get<Post[]>('http://localhost:8080/api/posts/by-user/' + name);
  }

  deletePost(id: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/api/posts/' + id);
  }

}
