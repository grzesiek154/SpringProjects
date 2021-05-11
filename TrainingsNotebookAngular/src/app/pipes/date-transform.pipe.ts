import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTransform'
})
export class DateTransformPipe implements PipeTransform {

  transform(date:string): string {
    let transformedDate = "no date proviaded";
    if(date != null) {
      transformedDate = date.replace("T", " ").slice(0,19)
    }
    return transformedDate;
  }

}
