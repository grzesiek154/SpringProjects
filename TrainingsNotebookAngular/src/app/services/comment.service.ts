import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentPayload } from '../models/CommentPayload';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getAllCommnetsForPost(postId: number): Observable<CommentPayload[]> {
    return this.http.get<CommentPayload[]>('http://localhost:8080/api/comments/by-post/' + postId);
  }

  postComment(commentPayload: CommentPayload): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/comments/', commentPayload);
  }

  getAllCommentsByUser(name: string) {
    return this.http.get<CommentPayload[]>('http://localhost:8080/api/comments/by-user/' + name);
  }
}
