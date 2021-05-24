import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'weekdays'
})
export class WeekdaysPipe implements PipeTransform {

  transform(value: string): string {
    let valueFormat = '';
    switch (value) {
      case 'Monday':
        valueFormat = 'Thứ hai';
        break;
      case 'Tuesday':
        valueFormat = 'Thứ ba';
        break;
      case 'Wednesday':
        valueFormat = 'Thứ tư';
        break;
      case 'Thursday':
        valueFormat = 'Thứ năm';
        break;
      case 'Friday':
        valueFormat = 'Thứ sáu';
        break;
      case 'Saturday':
        valueFormat = 'Thứ bảy';
        break;
      case 'Sunday':
        valueFormat = 'Chủ nhật';
        break;
    }
    return valueFormat;
  }

}
