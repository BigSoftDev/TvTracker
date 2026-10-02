import { Component, computed, inject, Input, input, OnInit } from '@angular/core';
import { Show } from '../../models/show';
import { MatButtonModule } from '@angular/material/button';
import { UserService } from '../../service/user.service';
import { UserShowService } from '../../service/userShow.service';
import { UserShowDto } from '../../models/userShowDto';
import { ShowAction } from '../../models/showAction';

@Component({
  selector: 'show-tile',
  standalone: true,
  templateUrl: './show-tile.component.html',
  styleUrls: ['./show-tile.component.css'],
  imports: [MatButtonModule]
})
export class ShowTileComponent {

  private userService = inject(UserService);
  private userShowService = inject(UserShowService);

  userShow = input<UserShowDto | null>(null);
  actions = input<ShowAction[]>([]);

  fullLink = computed(() => {

    const currentShow = this.userShow()?.show;

    if (!currentShow?.imgLink) return '';

    const base = 'https://artworks.thetvdb.com';

    return `${base}${currentShow.imgLink.startsWith('/') ? '' : '/'}${currentShow.imgLink}`;
  });

}
