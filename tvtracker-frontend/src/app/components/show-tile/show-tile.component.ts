import { Component, computed, Input, input, OnInit } from '@angular/core';
import { Series } from '../../models/series';

@Component({
  selector: 'show-tile',
  standalone: true,
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css']
})
export class ShowTileComponent {

  show = input<Series | null>(null);

  fullLink = computed(() => {

    const currentShow = this.show();

    if (!currentShow?.imageLink) return '';

    const base = 'https://artworks.thetvdb.com';

    return `${base}${currentShow.imageLink.startsWith('/') ? '' : '/'}${currentShow.imageLink}`;
  });
  constructor() { }

}
