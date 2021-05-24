import { FuseNavigation } from "@fuse/types";

export const navigation: FuseNavigation[] = [
  {
    id: "applications",
    title: "Applications",
    translate: "NAV.APPLICATIONS",
    type: "group",
    children: [
      {
        id: "management",
        title: "Management",
        translate: "NAV.MANAGEMENT",
        type: "collapsable",
        icon: "assignment_ind",
        children: [
          {
            id: "employees",
            title: "Employees",
            type: "item",
            url: "/apps/management/employees",
            exactMatch: true,
          },
          {
            id: "members",
            title: "Members",
            type: "item",
            url: "/apps/management/members",
            exactMatch: true,
          },
          {
            id: "categories",
            title: "Categories",
            type: "item",
            url: "/apps/management/categories",
            exactMatch: true,
          },
          {
            id: "products",
            title: "Products",
            type: "item",
            url: "/apps/management/products",
            exactMatch: true,
          },
        ],
      },
    ],
  },
];
