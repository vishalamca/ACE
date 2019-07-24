using System;
using System.Collections.Generic;
using System.Text;

namespace AccountInfoCrawler
{
     public class ContactCard
    {
        public string Name { get; set; }
        public string Title { get; set; }
        public string Company { get; set; }
        public string Email { get; set; }
        public string Phone { get; set; }
    public ContactCard(string name, string title, string company, string email=null, string phone=null)
    {
        Name = name; Title = title; Company = company; Email = email; Phone = phone;
    }
    }
}
