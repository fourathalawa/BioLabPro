import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router) { }


  ngOnInit(): void {
    // Sélectionnez tous les éléments <li> du menu
    const menuItems = document.querySelectorAll( '.menu-item');
    console.log("************");
    console.log(menuItems.length);
    console.log("************");

// Ajoutez un événement de clic à chaque élément <li>
    menuItems.forEach((item: Element, key: number, parent: NodeListOf<Element>) => {
      item.addEventListener('click', () => {
        // Supprimez la classe "active" de tous les éléments <li> du menu
        parent.forEach((item: Element, key: number, parent: NodeListOf<Element>) => {
          item.classList.remove('active');
        });

        // Ajoutez la classe "active" à l'élément <li> qui a été cliqué
        item.classList.add('menu-item active');
      });
    });


  }

  redirectToUsers() {
    this.router.navigate(['/back/users']); // Redirection vers la route 'accueil'
  }

}
