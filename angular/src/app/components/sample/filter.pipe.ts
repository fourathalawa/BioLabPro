import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(items: any[], searchTerm: string): any[] {
    if (!items || !searchTerm) {
      return items;
    }

    searchTerm = searchTerm.toLowerCase();

    return items.filter(item =>
      item.sampleID.toString().toLowerCase().includes(searchTerm)  ||
      //item.urgency.toLowerCase().includes(searchTerm) ||
      item.userID.userFirstName.toLowerCase().includes(searchTerm) /* ||
      item.dateofwithdrawl.toLowerCase().includes(searchTerm) ||
      item.userID.userLastName.toLowerCase().includes(searchTerm)   */
    );
  }

}
