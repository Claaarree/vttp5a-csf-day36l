import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { FileUploadService } from '../../services/file-upload.service';

@Component({
  selector: 'app-upload',
  standalone: false,
  templateUrl: './upload.component.html',
  styleUrl: './upload.component.css'
})
export class UploadComponent implements OnInit{
  form!: FormGroup;
  dataUri!: string;
  blob!: Blob;
  private router = inject(Router);
  private fb = inject(FormBuilder)
  private fileUploadSvc = inject(FileUploadService)

  ngOnInit(): void {
    this.createForm();
  }

  upload() {
    console.log("dataUri: " + this.dataUri);
    if(!this.dataUri) {
      return;
    }
    this.blob = this.dataURItoBlob(this.dataUri);
    const formValue = this.form.value;
    this.fileUploadSvc.upload(formValue, this.blob)
      .then((result) => {
        console.log(result);
        this.router.navigate(['/image', result.postId])
      })
  }

  onFileChange(event: Event) {
    console.log("onFileChange");
    const input = event.target as HTMLInputElement;
    if(input.files && input.files.length > 0) {
      // retrieving the first file using the index since there may be multiple files
      const file = input.files[0];
      console.log("file: " + file);
      const reader = new FileReader();
      reader.onload = () => {
        this.dataUri = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  createForm() {
    this.form = this.fb.group({
      comments: this.fb.control<string>('')
    });
  }

  dataURItoBlob(dataURI: string): Blob{
    const [meta, base64Data] = dataURI.split(',');
    const mimeMatch = meta.match(/:(.*?);/);

    const mimeType = mimeMatch ? mimeMatch[1] : 'application/octet-stream';
    const byteString = atob(base64Data);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for(let i = 0; i < byteString.length; i++){
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type: mimeType});
  }
}
