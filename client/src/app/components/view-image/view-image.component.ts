import { Component, inject, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FileUploadService } from '../../services/file-upload.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-image',
  standalone: false,
  templateUrl: './view-image.component.html',
  styleUrl: './view-image.component.css'
})
export class ViewImageComponent implements OnInit{
  postId = '';
  params$!: Subscription; //best practice to use subscription instead of just getting the value
  imageData: any;

  private actRoute = inject(ActivatedRoute);
  private fineUploadSvc = inject(FileUploadService);

  ngOnInit(): void {
    this.params$ = this.actRoute.params.subscribe(async(params) => {
      this.postId = params['postId'];
      let r = await this.fineUploadSvc.getImage(this.postId);
      this.imageData = r.image;
    })
  }
}
