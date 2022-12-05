import { Injectable, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import Swal from 'sweetalert2';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';

@Injectable()
export abstract class CommonListComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  title: string;
  list: E[];
  protected nameModel: string;

  totalRecords = 0;
  totalPerPage = 4;
  actualPage = 0;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  constructor(protected service: S) { }

  ngOnInit() {
    this.calculateRanges();
  }

  paginate(event: PageEvent): void {
    this.actualPage = event.pageIndex;
    this.totalPerPage = event.pageSize;
    this.calculateRanges();
  }

  private calculateRanges() {
    this.service.getAllPaginated(this.actualPage.toString(), this.totalPerPage.toString()).subscribe(p => {
      this.list = p.content as E[];
      this.totalRecords = p.totalElements as number;

    });
  }

  public delete(e:E):void {
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete ${e.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.service.delete(e.id).subscribe(() => {
          this.calculateRanges();
          Swal.fire(
            'Success!',
            `${this.nameModel} ${e.name} successfully deleted`,
            'success'
          );
        });
      }
    });
  }
}
