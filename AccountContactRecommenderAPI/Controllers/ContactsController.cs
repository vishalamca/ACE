using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using AccountInfoCrawler;
using System.Net.Http;
using HtmlAgilityPack;

namespace AccountContactEnrichment.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ContactsController : ControllerBase
    {
        // GET: api/Contacts
        [HttpGet]
        public async Task<IEnumerable<ContactCard>> Get(string companyName)
        {
            List<ContactCard> contactList = new List<ContactCard>();
            var url = "https://finance.yahoo.com/quote/"+companyName+"/profile?p=" + companyName;
            var httpClient = new HttpClient();
            var html = await httpClient.GetStringAsync(url);
            var htmlDocument = new HtmlDocument();
            htmlDocument.LoadHtml(html);
            // var lists = htmlDocument.DocumentNode.Descendants("a").Where(node => node.FirstChild().("data-test","").Equals("COMPANY_PROFILE"));
            var executivesList = htmlDocument.DocumentNode.SelectNodes("//table[@class='W(100%)']/tbody/tr").ToList();
            foreach (var executive in executivesList)
            {
                var execDetails = executive.ChildNodes;
                var tdNodeName = execDetails[0].InnerText;
                var tdNodeTitle = execDetails[1].InnerText;
                contactList.Add(new ContactCard(tdNodeName, tdNodeTitle, companyName));
            }
            return contactList;
        }

        // GET: api/Contacts/5
        [HttpGet("{id}", Name = "Get")]
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Contacts
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT: api/Contacts/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
