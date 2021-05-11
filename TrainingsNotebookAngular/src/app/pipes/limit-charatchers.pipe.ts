import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'limitCharatchers'
})
export class LimitCharatchersPipe implements PipeTransform {

  transform(text: string, limit: number): unknown {
    let slicedText = text.slice(0,limit) + "...";
    return slicedText;
  }

}
